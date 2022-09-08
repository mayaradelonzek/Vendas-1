package br.com.dionataferraz.vendas.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    fun login(email: String?, password: String?) {
        if (email.isNullOrBlank() && password.isNullOrBlank()) {
            error.value = true
        } else {

        }
    }

}