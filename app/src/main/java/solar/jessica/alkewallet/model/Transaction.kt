package solar.jessica.alkewallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val concept: String,
    val date: String,
    val to_account_id: Int,
    val user: String
)