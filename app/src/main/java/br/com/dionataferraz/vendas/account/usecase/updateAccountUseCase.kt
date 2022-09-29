package br.com.dionataferraz.vendas.account.usecase

import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity
import br.com.dionataferraz.vendas.account.data.local.Operation
import br.com.dionataferraz.vendas.transaction.data.TransactionEntity
import br.com.dionataferraz.vendas.transaction.usecase.InsertTransactionUsecase
import java.util.*

class updateAccountUseCase(private val acc: AccountEntity,
                           private val operation: Operation,
                           private val amount: Double) {

    private val database: AccountDatabase by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    private fun invoke() {
        val newTransaction = TransactionEntity(time = Date(),
            amount = amount,
            name = operation.name,
            type = operation)

        InsertTransactionUsecase(newTransaction).invoke()

        database.AccDao().updateAccount(acc)
    }

    fun withdraw() {
        acc.value -= amount
        invoke()
    }

    fun deposit() {
        acc.value += amount
        invoke()
    }

}