package solar.jessica.alkewallet.data.repository

import solar.jessica.alkewallet.data.local.AppDatabase
import solar.jessica.alkewallet.data.network.transaction.TransactionRequest
import solar.jessica.alkewallet.data.network.transaction.TransactionService
import solar.jessica.alkewallet.model.Transaction

class TransactionImpl(private val database: AppDatabase): TransactionRepository {
    override suspend fun transacciones(): List<Transaction> {
        return database.transactionsDao().obtenerTodos()
    }
}