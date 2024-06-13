package solar.jessica.alkewallet.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import solar.jessica.alkewallet.data.network.user.SignUpRequest
import solar.jessica.alkewallet.domain.UserUseCase
import solar.jessica.alkewallet.login.viewmodel.LoginViewModel
import solar.jessica.alkewallet.model.User
import java.lang.Exception

class SignupViewModel(private val userUseCase: UserUseCase): ViewModel() {
    //Notifica si el registro es exitoso
    val resultSignup = MutableLiveData<Boolean>()

    //Notifica errores
    val error = MutableLiveData<String>()

    fun signUp(name: String, lastName: String, email: String, password: String, repeatPassword: String) {
        //Reiniciamos el error
        error.value = ""
        if (name == "") { //Nombre vacío
            error.value = "Ingrese nombre"
            return
        }
        if (lastName == "") { //Apellido vacío
            error.value = "Ingrese apellido"
            return
        }
        if (email == "") { //Correo vacío
            error.value = "Ingrese correo"
            return
        }
        if (password == "") { //Contraseña vacío
            error.value = "Ingrese contraseña"
            return
        }
        if (repeatPassword == "") { //Repetir contraseña vacío
            error.value = "Repita contraseña"
            return
        }
        if (password != repeatPassword) { //Validamos contraseñas
            error.value = "Las contraseñas no coinciden"
            return
        }
        viewModelScope.launch {
            try {
                val usuario = SignUpRequest(name, lastName, email, password)
                userUseCase.crear(usuario)
                // Datos correctos, registro completado
                resultSignup.value = true
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}

class SignUpViewModelFactory(private val userUseCase: UserUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(userUseCase) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}