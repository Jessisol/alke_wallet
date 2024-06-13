package solar.jessica.alkewallet.data.repository

import retrofit2.Response
import solar.jessica.alkewallet.data.local.AppDatabase
import solar.jessica.alkewallet.data.network.user.Account
import solar.jessica.alkewallet.data.network.user.AccountResponse
import solar.jessica.alkewallet.data.network.user.LoginRequest
import solar.jessica.alkewallet.data.network.user.LoginResponse
import solar.jessica.alkewallet.data.network.user.SignUpRequest
import solar.jessica.alkewallet.data.network.user.TransferenciaRequest
import solar.jessica.alkewallet.data.network.user.TransferenciaResponse
import solar.jessica.alkewallet.data.network.user.UserResponse
import solar.jessica.alkewallet.data.network.user.UserService
import solar.jessica.alkewallet.model.Transaction
import solar.jessica.alkewallet.model.User
import java.util.Date

//Repositorio usuario, conecta un UseCase con apis remotas o base de datos locales
class UserImpl(private val service: UserService, private val accessToken: String?, private val database: AppDatabase): UserRepository {
    //Crear usuario
    override suspend fun crear(user: SignUpRequest): UserResponse? {
        return service.crear(user).body()
    }

    //Crear cuenta para el usuario
    override suspend fun crearCuenta(): Account? {
        val respuesta = service.crearCuenta("Bearer $accessToken")
        if (respuesta.isSuccessful) {
            return cuentas()?.first()
        } else {
            throw Exception(respuesta.message())
        }
    }

    //Inciar sesión
    override suspend fun logearse(user: LoginRequest): LoginResponse? {
        val respuesta = service.logearse(user)
        if (respuesta.isSuccessful) {
            return respuesta.body()
        } else {
            throw Exception(respuesta.message())
        }
    }

    //Obtenemos los datos del usuario
    override suspend fun informacionUsuario(): User? {
        val respuesta = service.informacionUsuario("Bearer $accessToken")
        if (respuesta.isSuccessful) {
            return respuesta.body()
        } else {
            throw Exception(respuesta.message())
        }
    }

    //Obtenemos las cuentas del usuario
    override suspend fun cuentas(): List<Account>? {
        val respuesta = service.cuentas("Bearer $accessToken")
        if (respuesta.isSuccessful) {
            return respuesta.body()
        } else {
            throw Exception(respuesta.message())
        }
    }

    override suspend fun transferir(
        id: Int,
        monto: Int,
        operacion: String,
        mensaje: String
    ): TransferenciaResponse? {
        val authHeader = "Bearer $accessToken"
        //Utilizamos la misma cuenta del usuario para transferir o depositar
        val cuentaId = service.cuentas(authHeader).body()?.first()?.id
        if (cuentaId != null) {
            val transferenciaRequest = TransferenciaRequest(operacion, mensaje, monto)
            val respuesta = service.transferir(authHeader, cuentaId, transferenciaRequest)
            if (respuesta.isSuccessful) {
                val transferencia = Transaction(amount = monto.toDouble(), concept = operacion, date = Date().toString(), to_account_id = cuentaId, user = "Yo")
                database.transactionsDao().insertar(transferencia)
                return respuesta.body()
            } else {
                throw Exception(respuesta.message())
            }
        } else {
            throw Exception("Sin cuenta creada")
        }
    }
}