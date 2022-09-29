package br.com.dionataferraz.vendas.transaction.usecase

import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.transaction.data.TransactionDatabase
import br.com.dionataferraz.vendas.transaction.data.TransactionEntity

class InsertTransactionUsecase(private val trans: TransactionEntity) {

    private val database: TransactionDatabase by lazy {
        TransactionDatabase.getInstance(context = App.context)
    }

    fun invoke() {
        database.DAO().insertTransaction(trans)
    }

}