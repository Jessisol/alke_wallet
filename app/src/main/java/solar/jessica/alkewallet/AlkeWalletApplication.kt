package solar.jessica.alkewallet

import android.app.Application
import androidx.room.Room
import solar.jessica.alkewallet.data.local.AppDatabase

class AlkeWalletApplication: Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Configurar la base de datos Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }
}