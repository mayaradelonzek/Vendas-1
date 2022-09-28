package br.com.dionataferraz.vendas.account.usecase

import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.account.data.local.AccountDatabase
import br.com.dionataferraz.vendas.account.data.local.AccountEntity

class findAccountUseCase(private val id: Int) {

    private val database by lazy {
        AccountDatabase.getInstance(context = App.context)
    }

    fun invoke(): AccountEntity {
        return database.AccDao().getAccount(id)
    }

}