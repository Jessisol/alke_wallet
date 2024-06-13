package solar.jessica.alkewallet.domain

import solar.jessica.alkewallet.data.repository.TransactionRepository
import solar.jessica.alkewallet.model.Transaction

class TransactionUseCase(private val repository: TransactionRepository) {
    suspend fun transactions(): List<Transaction> {
        return repository.transacciones()
    }
}