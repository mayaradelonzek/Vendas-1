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

    fun gasStationAcc(id: Int, amount: Double) {
        try {
            val acc = findAccountUseCase(id).invoke()

            val newTransaction = TransactionRequest(
                value = amount,
                description = TransactionType.GAS_STATION.name,
                transactionType = TransactionType.GAS_STATION
            )

            viewModelScope.launch() {
                InsertTransactionUsecase(newTransaction).register()
            }

            updateAccountUseCase(
                acc = acc,
                amount = amount).credit()
            success.value = amount
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    fun pubAcc(id: Int, amount: Double) {
        try {
            val acc = findAccountUseCase(id).invoke()

            val newTransaction = TransactionRequest(
                value = amount,
                description = TransactionType.PUB.name,
                transactionType = TransactionType.PUB
            )

            viewModelScope.launch() {
                InsertTransactionUsecase(newTransaction).register()
            }

            updateAccountUseCase(
                acc = acc,
                amount = amount).credit()
            success.value = amount
        } catch (e: RuntimeException) {
            println(e.message)
        }
    }

    fun marketAcc(id: Int, amount: Double) {
        val acc = findAccountUseCase(id).invoke()

        val newTransaction = TransactionRequest(
            value = amount,
            description = TransactionType.MARKET.name,
            transactionType = TransactionType.MARKET
        )

        viewModelScope.launch() {
            InsertTransactionUsecase(newTransaction).register()
        }

        updateAccountUseCase(
            acc = acc,
            amount = amount).credit()
        success.value = amount
    }

//    @Throws(RuntimeException::class)
//    private fun validateWithdraw(withdrawal: Double, balance: Double) {
//        if (withdrawal > balance) {
//            error.value = "Valor do saque deve ser menor que o saldo"
//            throw RuntimeException("Valor do saque deve ser menor que o saldo")
//        }
//    }
}
