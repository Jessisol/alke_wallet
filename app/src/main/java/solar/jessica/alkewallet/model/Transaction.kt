package solar.jessica.alkewallet.model

data class Transaction(
    val amount: Double,
    val concept: String,
    val date: String,
    val user: User
)