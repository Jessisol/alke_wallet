package solar.jessica.alkewallet.data.network.user

//Objeto que nos permite transferir o depositar
data class TransferenciaRequest(
    val type: String,
    val concept: String,
    val amount: Int
)