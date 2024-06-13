package solar.jessica.alkewallet.data.network.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import solar.jessica.alkewallet.model.User

//Servicio REST que nos permite consultar a la api de wallet
interface UserService {
    @POST("users")
    suspend fun crear(@Body user: SignUpRequest): Response<UserResponse>

    @POST("accounts")
    suspend fun crearCuenta(@Header("Authorization") authHeader: String): Response<AccountResponse>

    @GET("accounts/me")
    suspend fun cuentas(@Header("Authorization") authHeader: String): Response<List<Account>>

    @POST("accounts/{id}")
    suspend fun transferir(@Header("Authorization") authHeader: String, @Path("id") id: Int, @Body transferencia: TransferenciaRequest): Response<TransferenciaResponse>

    @POST("auth/login")
    suspend fun logearse(@Body user: LoginRequest): Response<LoginResponse>

    @GET("auth/me")
    suspend fun informacionUsuario(@Header("Authorization") authHeader: String): Response<User>
}