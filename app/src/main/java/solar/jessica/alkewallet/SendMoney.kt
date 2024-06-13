package solar.jessica.alkewallet

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import solar.jessica.alkewallet.data.network.RetrofitHelper
import solar.jessica.alkewallet.data.network.user.UserService
import solar.jessica.alkewallet.data.repository.TransactionImpl
import solar.jessica.alkewallet.data.repository.UserImpl
import solar.jessica.alkewallet.databinding.ActivitySendMoneyBinding
import solar.jessica.alkewallet.databinding.ActivitySignupPageBinding
import solar.jessica.alkewallet.domain.TransactionUseCase
import solar.jessica.alkewallet.domain.UserUseCase
import solar.jessica.alkewallet.home.viewmodel.HomeViewModel
import solar.jessica.alkewallet.home.viewmodel.HomeViewModelFactory
import solar.jessica.alkewallet.signup.SignUpViewModelFactory
import solar.jessica.alkewallet.signup.SignupViewModel

class SendMoney : AppCompatActivity() {
    //Acceso a nuestras vistas
    lateinit var binding: ActivitySendMoneyBinding
    //Viewmodel con validaciones
    lateinit var viewModel: TransferenciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Iniciamos el binding
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Iniciamos el viewmodel
        val preferencias = getSharedPreferences("app", Context.MODE_PRIVATE)
        val accessToken = preferencias.getString("accessToken", "")
        if (accessToken != null) {
            val servicio = RetrofitHelper.retrofit().create(UserService::class.java)
            val repositorioUser = UserImpl(servicio, accessToken, AlkeWalletApplication.database)
            val useCase = UserUseCase(repositorioUser)
            viewModel = TransferirViewModelFactory(useCase, "payment").create(TransferenciaViewModel::class.java)
        }

        viewModel.mensaje.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.resultadoTransferencia.observe(this) {
            if (it) {
                finish()
            }
        }

        binding.imageViewEnviar.setOnClickListener {
            viewModel.transferir(binding.editTextMonto.text.toString(), binding.editTextNotas.text.toString())
        }
        binding.back.setOnClickListener {
            finish()
        }
    }
}