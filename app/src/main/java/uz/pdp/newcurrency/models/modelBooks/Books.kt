package uz.pdp.newcurrency.models.modelBooks

data class Books(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)