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
import solar.jessica.alkewallet.data.network.RetrofitHelper
import solar.jessica.alkewallet.data.network.user.UserService
import solar.jessica.alkewallet.data.repository.UserImpl
import solar.jessica.alkewallet.databinding.ActivitySignupPageBinding
import solar.jessica.alkewallet.domain.UserUseCase
import solar.jessica.alkewallet.login.viewmodel.LoginViewModel
import solar.jessica.alkewallet.login.viewmodel.LoginViewModelFactory
import solar.jessica.alkewallet.signup.SignUpViewModelFactory
import solar.jessica.alkewallet.signup.SignupViewModel

class SignupPage : AppCompatActivity() {
    //Acceso a nuestras vistas
    lateinit var binding: ActivitySignupPageBinding
    //Viewmodel con validaciones
    lateinit var viewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Iniciamos el binding
        binding = ActivitySignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Iniciamos el viewmodel
        //viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val servicio = RetrofitHelper.retrofit().create(UserService::class.java)
        val repositorio = UserImpl(servicio, null, AlkeWalletApplication.database)
        val useCase = UserUseCase(repositorio)
        viewModel = SignUpViewModelFactory(useCase).create(SignupViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Reemplazamos findViewById
        binding.buttonHome.setOnClickListener {
            //Pasamos nombre, apellido, correo, contraseñas para validar en el viewmodel
            viewModel.signUp(
                binding.editTextName.text.toString(),
                binding.editTextLastname.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString(),
                binding.editTextRepeatPassword.text.toString()
            )
        }
        binding.textViewUltimos.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        //Observamos resultado del registro
        viewModel.resultSignup.observe(this) {
            if (it) { //Registro exitoso
                //Ir al login
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        //Observamos errores
        viewModel.error.observe(this) {
            //Si el error no está vacío se lo mostramos al usuario
            if (it != "") Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}