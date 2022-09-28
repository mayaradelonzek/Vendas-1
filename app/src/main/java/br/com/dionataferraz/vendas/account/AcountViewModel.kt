package br.com.dionataferraz.vendas.account

import androidx.lifecycle.LiveData
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

    fun withdrawAcc(id: Int, amount: Double) {
        try {
            val acc = findAccountUseCase(id).invoke()
            validateWithdraw(amount, acc.value)

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
                amount = amount).withdraw()
            success.value = amount
        } catch (e: RuntimeException) {
            println(e.message)
        }

    }

    fun depositAcc(id: Int, amount: Double) {
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
            amount = amount).deposit()
        success.value = amount
    }

    @Throws(RuntimeException::class)
    private fun validateWithdraw(withdrawal: Double, balance: Double) {
        if (withdrawal > balance) {
            error.value = "Valor do saque deve ser menor que o saldo"
            throw RuntimeException("Valor do saque deve ser menor que o saldo")
        }
    }
}
