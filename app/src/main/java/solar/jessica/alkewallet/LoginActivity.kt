package solar.jessica.alkewallet

import android.content.Context
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
import solar.jessica.alkewallet.data.network.RetrofitHelper
import solar.jessica.alkewallet.data.network.user.UserService
import solar.jessica.alkewallet.data.repository.UserImpl
import solar.jessica.alkewallet.databinding.ActivityLoginBinding
import solar.jessica.alkewallet.domain.UserUseCase
import solar.jessica.alkewallet.login.viewmodel.LoginViewModel
import solar.jessica.alkewallet.login.viewmodel.LoginViewModelFactory

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
        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val servicio = RetrofitHelper.retrofit().create(UserService::class.java)
        val repositorio = UserImpl(servicio, null, AlkeWalletApplication.database)
        val useCase = UserUseCase(repositorio)
        viewModel = LoginViewModelFactory(useCase).create(LoginViewModel::class.java)

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
                finish()
            }
        }
        //Observamos token
        viewModel.accessToken.observe(this) {
            if (it != null) {
                val preferencias = getSharedPreferences("app", Context.MODE_PRIVATE).edit()
                preferencias.putString("accessToken", it)
                preferencias.apply()
            }
        }
        viewModel.error.observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}