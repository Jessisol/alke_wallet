package solar.jessica.alkewallet.data.network.user

//Respuesta crear usuario
data class UserResponse(
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val error: String
)
