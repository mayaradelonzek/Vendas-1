package br.com.dionataferraz.vendas.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dionataferraz.vendas.account.data.Account

class AccountViewModel : ViewModel() {

    var error: MutableLiveData<String> = MutableLiveData()
    var account = MutableLiveData<Account>()

    fun validateAcc(description: String?, value: Double, responsible: String?, credit: Boolean, debit: Boolean) {
        if (description.isNullOrBlank()) {
            error.value = "Preencha o campo Nome da Conta"
            return
        }

        if (value <= 0) {
            error.value = "Preencha o campo Saldo com valor maior que zero"
            return
        }

        if (responsible.isNullOrBlank()) {
            error.value = "Preencha o campo Responsável da Conta"
            return
        }

        if (!credit && !debit) {
            error.value = "Preencha umas das opções de conta"
            return
        }

        val accCreated = Account(
            description = description,
            value = value,
            responsible = responsible,
            credit = credit,
            debit = debit
        )
        account.value = accCreated
    }
}
