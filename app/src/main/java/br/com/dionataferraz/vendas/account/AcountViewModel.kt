package br.com.dionataferraz.vendas.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dionataferraz.vendas.account.data.Account

class AccountViewModel : ViewModel() {

    private val error: MutableLiveData<String> = MutableLiveData()
    val shouldShowError: LiveData<String> = error
    private val account: MutableLiveData<Account> = MutableLiveData()
    val accountLiveData: LiveData<Account> = account

    fun createAcc(description: String?, value: Double?, responsible: String?, credit: Boolean, debit: Boolean) {
        if (description.isNullOrBlank())
            error.value = "Preencha o nome da conta"
        else if (value != null) {
            if (value > 0) {
                error.value = "O saldo deve ser maior que zero"
            } else if (responsible.isNullOrBlank())
                error.value = "Preencha o reponsável da conta"
            else {
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
    }
}
