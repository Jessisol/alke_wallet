package solar.jessica.alkewallet.data.repository

import solar.jessica.alkewallet.data.network.user.Account
import solar.jessica.alkewallet.data.network.user.LoginRequest
import solar.jessica.alkewallet.data.network.user.LoginResponse
import solar.jessica.alkewallet.data.network.user.SignUpRequest
import solar.jessica.alkewallet.data.network.user.TransferenciaResponse
import solar.jessica.alkewallet.data.network.user.UserResponse
import solar.jessica.alkewallet.model.User

//Declaración repositorio usuario
interface UserRepository {
    suspend fun crear(user: SignUpRequest): UserResponse?
    suspend fun crearCuenta(): Account?
    suspend fun cuentas(): List<Account>?
    suspend fun logearse(user: LoginRequest): LoginResponse?
    suspend fun informacionUsuario(): User?
    suspend fun transferir(id: Int, monto: Int, operacion: String, mensaje: String): TransferenciaResponse?
}