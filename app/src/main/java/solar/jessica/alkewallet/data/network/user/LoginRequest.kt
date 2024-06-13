package solar.jessica.alkewallet.data.network.user

//Objeto que permite iniciar sesión
data class LoginRequest(
    val email: String,
    val password: String
)