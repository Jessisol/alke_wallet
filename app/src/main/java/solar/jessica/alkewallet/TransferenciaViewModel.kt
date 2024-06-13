package solar.jessica.alkewallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import solar.jessica.alkewallet.domain.UserUseCase

class TransferenciaViewModel(private val userUseCase: UserUseCase, private val operacion: String): ViewModel() {
    val mensaje = MutableLiveData<String?>()
    val resultadoTransferencia = MutableLiveData<Boolean>()

    fun transferir(monto: String, mensajeTransferencia: String) {
        val montoInt: Int
        try {
            montoInt = monto.toInt()
        } catch (e: Exception) {
            mensaje.value = "Ingrese monto"
            return
        }
        if (montoInt == 0) { //Nombre vacío
            mensaje.value = "Ingrese monto"
            return
        }
        if (mensajeTransferencia == "") { //Nombre vacío
            mensaje.value = "Ingrese nombre"
            return
        }
        viewModelScope.launch {
            try {
                userUseCase.transferir(0, montoInt, operacion, mensajeTransferencia)
                resultadoTransferencia.value = true
            } catch (e: Exception) {
                mensaje.value = e.message
            }
        }
    }
}

class TransferirViewModelFactory(private val userUseCase: UserUseCase, private val operacion: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransferenciaViewModel::class.java)) {
            return TransferenciaViewModel(userUseCase, operacion) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}