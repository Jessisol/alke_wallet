package solar.jessica.alkewallet.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import solar.jessica.alkewallet.data.network.user.Account
import solar.jessica.alkewallet.domain.TransactionUseCase
import solar.jessica.alkewallet.domain.UserUseCase
import solar.jessica.alkewallet.model.Transaction
import solar.jessica.alkewallet.model.User
import solar.jessica.alkewallet.signup.SignupViewModel

class HomeViewModel(private val userUseCase: UserUseCase, private val transactionUseCase: TransactionUseCase): ViewModel() {
    //Notificar sobre el usuario de la cuenta
    val user = MutableLiveData<User>()
    val mensaje = MutableLiveData<String?>()
    val cuenta = MutableLiveData<Account>()
    val transactions = MutableLiveData<List<Transaction>>()

    init {
        cargarUsuario()
        cargarCuenta()
        cargarTransacciones()
    }

    fun cargarCuenta() {
        viewModelScope.launch {
            try {
                val cuentas = userUseCase.cuentas()
                if (cuentas?.isEmpty() == true) {
                    cuenta.value = userUseCase.crearCuenta()
                } else {
                    cuenta.value = cuentas?.first()
                }
            } catch (e: Exception) {
                mensaje.value = e.message
            }
        }
    }

    fun cargarUsuario() {
        viewModelScope.launch {
            try {
                user.value = userUseCase.informacionUsuario()
            } catch (e: Exception) {
                mensaje.value = e.message
            }
        }
    }

    fun cargarTransacciones() {
        viewModelScope.launch {
            transactions.value = transactionUseCase.transactions()
        }
    }

    //Notificar transacciones
    /*private val _transactions = MutableLiveData(
        listOf(
            //Simulamos transacciones
            Transaction(15.0, "-", "Oct 14, 10:24 AM", User("", "Yara Khalil", "", 0)),
            Transaction(20.50, "+", "Oct 12, 02:13 PM", User("", "Sara Ibrahim", "", 0)),
            Transaction(12.40, "+", "Oct 11, 01:19 AM", User("", "Ahmad Ibrahim", "", 0)),
            Transaction(21.30, "-", "Oct 07, 09:10 PM", User("", "Reem Khaled", "", 0)),
            Transaction(09.0, "+", "Oct 04, 05:45 AM", User("", "Hiba Saleh", "", 0)),
        )
        emptyList<Transaction>()
    )*/
}

//Factory sacado de la clase resumen para el examen
class HomeViewModelFactory(private val userUseCase: UserUseCase, private val transactionUseCase: TransactionUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(userUseCase, transactionUseCase) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}