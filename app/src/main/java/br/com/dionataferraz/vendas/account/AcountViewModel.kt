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
            val acc = findAccountUseCase(id).invoke()

            val newTransaction = TransactionRequest(
                value = value,
                description = type.value,
                transactionType = type
            )

            viewModelScope.launch() {
                InsertTransactionUsecase(newTransaction).register()
            }

            updateAccountUseCase(
                acc = acc,
                amount = value).credit()
            success.value = value
        } catch (e: RuntimeException) {
            println(e.message)
            error.value = e.message
        }
    }

//    @Throws(RuntimeException::class)
//    private fun validateWithdraw(withdrawal: Double, balance: Double) {
//        if (withdrawal > balance) {
//            error.value = "Valor do saque deve ser menor que o saldo"
//            throw RuntimeException("Valor do saque deve ser menor que o saldo")
//        }
//    }
}
