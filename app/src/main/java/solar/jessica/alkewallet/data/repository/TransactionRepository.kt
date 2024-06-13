package solar.jessica.alkewallet.data.repository

import solar.jessica.alkewallet.model.Transaction

interface TransactionRepository {
    suspend fun transacciones(): List<Transaction>
}