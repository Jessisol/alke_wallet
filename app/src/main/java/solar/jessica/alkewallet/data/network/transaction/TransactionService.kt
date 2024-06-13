package solar.jessica.alkewallet.data.network.transaction

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionService {
    @POST("transactions")
    fun transferir(@Header("Authorization") authHeader: String, @Body transactionRequest: TransactionRequest)
}