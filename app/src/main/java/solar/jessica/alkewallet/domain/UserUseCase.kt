package solar.jessica.alkewallet.domain

import solar.jessica.alkewallet.data.network.user.Account
import solar.jessica.alkewallet.data.network.user.LoginResponse
import solar.jessica.alkewallet.data.network.user.LoginRequest
import solar.jessica.alkewallet.data.network.user.SignUpRequest
import solar.jessica.alkewallet.data.network.user.TransferenciaResponse
import solar.jessica.alkewallet.data.network.user.UserResponse
import solar.jessica.alkewallet.data.repository.UserRepository
import solar.jessica.alkewallet.model.User

class UserUseCase(val repository: UserRepository) {
    suspend fun crear(user: SignUpRequest): UserResponse? {
        return repository.crear(user)
    }
    suspend fun logearse(user: LoginRequest): LoginResponse? {
        return repository.logearse(user)
    }

    suspend fun crearCuenta(): Account? {
        return repository.crearCuenta()
    }

    suspend fun cuentas(): List<Account>? {
        return repository.cuentas()
    }

    suspend fun informacionUsuario(): User? {
        return repository.informacionUsuario()
    }

    suspend fun transferir(id: Int, monto: Int, operacion: String, mensaje: String): TransferenciaResponse? {
        return repository.transferir(id, monto, operacion, mensaje)
    }
}