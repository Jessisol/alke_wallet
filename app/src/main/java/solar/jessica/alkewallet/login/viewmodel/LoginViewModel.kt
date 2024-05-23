package solar.jessica.alkewallet.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    //Notifica si el login es exitoso
    val resultLogin = MutableLiveData<Boolean>()
    fun hacerLogin(email: String, password: String) {
        //Simulamos que un inicio de sesión
        if (email == "test@test.cl" && password == "1234") {
            //Datos correctos
            resultLogin.value = true
        } else {
            //Datos incorrectos
            resultLogin.value = false
        }
    }
}