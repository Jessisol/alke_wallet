package solar.jessica.alkewallet.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import solar.jessica.alkewallet.data.network.user.LoginRequest
import solar.jessica.alkewallet.domain.UserUseCase
import java.lang.Exception

class LoginViewModel(private val userUseCase: UserUseCase): ViewModel() {
    //Notifica si el login es exitoso
    val resultLogin = MutableLiveData<Boolean>()
    val accessToken = MutableLiveData<String?>()
    val error = MutableLiveData<String?>()

    fun hacerLogin(email: String, password: String) {
        //Simulamos que un inicio de sesión
        viewModelScope.launch {
            error.value = null
            try {
                val usuario = LoginRequest(email, password)
                val respuesta = userUseCase.logearse(usuario)
                accessToken.value = respuesta?.accessToken
                resultLogin.value = true
            } catch (e: Exception) {
                error.value = e.message
                resultLogin.value = false
            }
        }
    }
}

class LoginViewModelFactory(private val userUseCase: UserUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userUseCase) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}