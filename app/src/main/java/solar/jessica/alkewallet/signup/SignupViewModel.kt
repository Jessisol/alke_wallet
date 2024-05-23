package solar.jessica.alkewallet.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel: ViewModel() {
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
        // Datos correctos, registro completado
        resultSignup.value = true
    }
}