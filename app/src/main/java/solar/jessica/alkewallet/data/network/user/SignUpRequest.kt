package solar.jessica.alkewallet.data.network.user

//Objeto que nos permite crear una cuenta
data class SignUpRequest (
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String
)