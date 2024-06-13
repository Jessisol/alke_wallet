package solar.jessica.alkewallet.data.network.user

//Cuenta del usuario, necesaria para transferencias y depósitos
data class Account(
    val id: Int,
    val creation_date: String,
    val money: Int,
    val is_blocked: Boolean,
    val user_id: Int
)