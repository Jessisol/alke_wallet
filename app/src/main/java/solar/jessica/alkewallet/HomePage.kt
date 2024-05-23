package solar.jessica.alkewallet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import solar.jessica.alkewallet.databinding.ActivityHomePageBinding
import solar.jessica.alkewallet.home.viewmodel.HomeViewModel
import solar.jessica.alkewallet.model.Transaction

class HomePage : AppCompatActivity() {
    //Acceso a nuestras vistas
    lateinit var binding: ActivityHomePageBinding
    //Viewmodel con datos
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Iniciamos el binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Reemplazamos findViewById
        binding.imageViewUsuario.setOnClickListener {
            startActivity(Intent(this, ProfilePage::class.java))
        }
        binding.imageViewIngresarDinero.setOnClickListener {
            startActivity(Intent(this, RequestMoney::class.java))
        }
        binding.imageViewEnviarDinero.setOnClickListener {
            startActivity(Intent(this, SendMoney::class.java))
        }

        //Iniciamos el viewmodel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        //Observamos el usuario
        viewModel.user.observe(this) {
            binding.textViewNombre.text = "Hola, " + it.name
            binding.textViewBalance.text = "$" + it.balance
        }

        //Observamos las transacciones
        viewModel.transactions.observe(this) {
            //Creamos un layout manager
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            //Creamos un adaptador
            binding.recyclerView.adapter = TransactionsAdapter(it)
        }
    }

    //Adaptador de transacciones, se crea con un listado de transacciones
    class TransactionsAdapter(val transactions: List<Transaction>): RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {
        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            //Imagen del usuario
            val imageViewUser: ImageView
            //Imagen de recibir o enviar dinero
            val imageViewConcept: ImageView
            //Nombre del usuario
            val textViewUserName: TextView
            //Fecha de la transacción
            val textViewDate: TextView
            //Monto de la transacción
            val textViewAmount: TextView

            init {
                //Iniciamos vistas
                imageViewUser = view.findViewById(R.id.imageView_user)
                imageViewConcept = view.findViewById(R.id.imageView_concept)
                textViewUserName = view.findViewById(R.id.textView_name)
                textViewDate = view.findViewById(R.id.textView_date)
                textViewAmount = view.findViewById(R.id.texto_monto)
            }

            fun bind(transaction: Transaction) {
                //Utilizamos concept para distinguir si es recibe o envía dinero
                val isPayment = transaction.concept == "+"
                //Icono para distinguir transacción
                imageViewConcept.setImageResource(if (isPayment) R.drawable.request_icon else R.drawable.send_icon)
                //Nombre del usuario
                textViewUserName.text = transaction.user.name
                //Fecha de la transacción
                textViewDate.text = transaction.date
                //Monto de la transacción
                textViewAmount.text = transaction.concept + "$" + transaction.amount
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            //Cargamos layout
            val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item_layout, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            //Cantidad transacciones
            return transactions.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //Obtenemos la transacción del listado de transacciones
            val transaction = transactions[position]
            //Cargamos la transacción
            holder.bind(transaction)
        }
    }
}