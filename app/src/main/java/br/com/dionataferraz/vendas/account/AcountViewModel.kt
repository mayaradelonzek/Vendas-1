package br.com.dionataferraz.vendas.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dionataferraz.vendas.account.data.Account
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.account.data.local.Operation
import br.com.dionataferraz.vendas.account.usecase.findAccountUseCase
import br.com.dionataferraz.vendas.account.usecase.updateAccountUseCase

class AccountViewModel : ViewModel() {

    var error = MutableLiveData<String>()
    var success = MutableLiveData<Double>()

    fun withdrawAcc(id: Int, amount: Double) {
        try {
            val acc = findAccountUseCase(id).invoke()
            validateWithdraw(amount, acc.value)
            updateAccountUseCase(
                acc = acc,
                operation = Operation.WITHDRAW,
                amount = amount).withdraw()
            success.value = amount
        } catch (e: RuntimeException) {
            println(e.message)
        }

    }

    fun depositAcc(id: Int, amount: Double) {
        val acc = findAccountUseCase(id).invoke()
        updateAccountUseCase(
            acc = acc,
            operation = Operation.DEPOSIT,
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
