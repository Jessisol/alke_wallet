package solar.jessica.alkewallet.data.network.transaction

data class TransactionRequest(
    val amount: Int,
    val concept: String,
    val date: String,
    val type: String,
    val account_id: Int,
    val user_id: Int,
    val to_account_id: Int
)