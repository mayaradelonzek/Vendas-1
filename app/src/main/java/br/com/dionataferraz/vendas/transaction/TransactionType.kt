package br.com.dionataferraz.vendas.transaction

enum class TransactionType(val value: String) {

    MARKET("Mercado"),
    GAS_STATION("Posto de gasolina"),
    PUB("Bar");

    override fun toString() : String {
        return value
    }

    companion object {
        fun getEnumByValue(valueToFind: String): TransactionType {
            return values().find { it.value.equals(valueToFind) }
                ?: throw IllegalArgumentException("Bad transaction value: $valueToFind")
        }
    }

}