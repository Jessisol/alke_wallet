package solar.jessica.alkewallet.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Ayuda a crear la api REST de wallet
object RetrofitHelper {
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://wallet-main.eba-ccwdurgr.us-east-1.elasticbeanstalk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}