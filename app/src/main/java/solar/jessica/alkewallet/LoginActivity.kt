package solar.jessica.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import solar.jessica.alkewallet.databinding.ActivityLoginBinding
import solar.jessica.alkewallet.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    //Acceso a nuestras vistas
    lateinit var binding: ActivityLoginBinding
    //Viewmodel con validaciones
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Iniciamos el binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logea)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Iniciamos el viewmodel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //Reemplazamos findViewById
        binding.buttonLogin.setOnClickListener {
            //Pasamos email y constraseña para validar en el viewmodel
            viewModel.hacerLogin(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
        }
        binding.textViewUltimos.setOnClickListener {
            startActivity(Intent(this, SignupPage::class.java))
        }

        //Observamos resultado del login
        viewModel.resultLogin.observe(this) {
            if (it) {
                //Ir al inicio
                startActivity(Intent(this, HomePage::class.java))
            } else {
                //Correo y/o constraseña incorrectos
                Toast.makeText(this, "Revise los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}