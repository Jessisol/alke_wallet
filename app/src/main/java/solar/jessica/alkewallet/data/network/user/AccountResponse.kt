package solar.jessica.alkewallet.data.network.user

//Respuesta creación cuenta
data class AccountResponse(
    val id: Int,
    val user_id: Int,
    val updated_at: String,
    val created_at: String
)