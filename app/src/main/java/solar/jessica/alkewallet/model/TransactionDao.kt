package solar.jessica.alkewallet.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//Objeto que nos permite operar en la base de datos
@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    suspend fun obtenerTodos(): List<Transaction>

    @Insert
    suspend fun insertar(transaction: Transaction)
}