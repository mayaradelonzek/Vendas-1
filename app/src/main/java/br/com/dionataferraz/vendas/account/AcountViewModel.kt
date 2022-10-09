package br.com.dionataferraz.vendas.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.account.usecase.findAccountUseCase
import br.com.dionataferraz.vendas.account.usecase.updateAccountUseCase
import br.com.dionataferraz.vendas.transaction.TransactionType
import br.com.dionataferraz.vendas.transaction.data.TransactionRequest
import br.com.dionataferraz.vendas.transaction.usecase.InsertTransactionUsecase
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    var error = MutableLiveData<String>()
    var success = MutableLiveData<Double>()

    fun registerItemBought(id: Int, value: Double, type: TransactionType) {
        try {
            validateCredit(value)
            val acc = findAccountUseCase().invoke(id)

            val newTransaction = TransactionRequest(
                value = value,
                description = type.value,
                transactionType = type
            )

            viewModelScope.launch() {
                InsertTransactionUsecase(newTransaction).register()
            }

            updateAccountUseCase().credit(
                acc = acc,
                amount = value)
            success.value = value
        } catch (e: RuntimeException) {
            println(e.message)
            error.value = e.message
        }
    }

    @Throws(RuntimeException::class)
    private fun validateCredit(value: Double) {
        val valueSplit = value.toString().split(".");

        if (valueSplit.size > 1 && valueSplit[1].length > 2) {
            throw RuntimeException("Valor deve ter, no máximo, duas casas decimais")
        }

        if (value <= 0) {
            throw RuntimeException("Valor da compra deve ser maior que zero")
        }
    }
}
