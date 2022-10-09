package br.com.dionataferraz.vendas.login.domain.usecase

import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.login.data.local.UserEntity
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase

class GetUserUsecase {

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance(context = App.context)
    }

    fun getLoggedUser(): UserEntity {
        return database.DAO().getUser().get(0)
    }

}