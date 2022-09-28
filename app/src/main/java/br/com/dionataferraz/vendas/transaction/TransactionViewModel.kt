package br.com.dionataferraz.vendas.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.transaction.data.TransactionResponse
import br.com.dionataferraz.vendas.transaction.usecase.DeleteTransactionUseCase
import br.com.dionataferraz.vendas.transaction.usecase.FindAllTransactionsUseCase
import kotlinx.coroutines.launch

class TransactionViewModel : ViewModel() {

    private val error: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = error

    private val transactions: MutableLiveData<List<TransactionResponse>?> = MutableLiveData()
    val transactionList: LiveData<List<TransactionResponse>?> = transactions

    private val findAllUseCase by lazy {
        FindAllTransactionsUseCase()
    }

    private val deleteUseCase by lazy {
        DeleteTransactionUseCase()
    }

    fun updateTrasactionView() {
        viewModelScope.launch() {
            transactions.value = findAllUseCase.findAll().get()
        }
    }

    fun delete(transactionId: Int) {
        viewModelScope.launch() {
            deleteUseCase.delete(transactionId)
            updateTrasactionView()
        }
    }

}