package solar.jessica.alkewallet.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import solar.jessica.alkewallet.model.Transaction
import solar.jessica.alkewallet.model.TransactionDao

//Base de datos local
@Database(entities = [Transaction::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun transactionsDao(): TransactionDao
}