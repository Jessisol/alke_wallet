package solar.jessica.alkewallet.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import solar.jessica.alkewallet.model.Transaction
import solar.jessica.alkewallet.model.User

class HomeViewModel: ViewModel() {
    //Notificar sobre el usuario de la cuenta
    private val _user = MutableLiveData(User("", "Jessica", "jessica@jessica.com", 1000000))
    val user = _user

    //Notificar transacciones
    private val _transactions = MutableLiveData(
        listOf(
            //Simulamos transacciones
            Transaction(15.0, "-", "Oct 14, 10:24 AM", User("", "Yara Khalil", "", 0)),
            Transaction(20.50, "+", "Oct 12, 02:13 PM", User("", "Sara Ibrahim", "", 0)),
            Transaction(12.40, "+", "Oct 11, 01:19 AM", User("", "Ahmad Ibrahim", "", 0)),
            Transaction(21.30, "-", "Oct 07, 09:10 PM", User("", "Reem Khaled", "", 0)),
            Transaction(09.0, "+", "Oct 04, 05:45 AM", User("", "Hiba Saleh", "", 0)),
        )
    )

    val transactions = _transactions
}