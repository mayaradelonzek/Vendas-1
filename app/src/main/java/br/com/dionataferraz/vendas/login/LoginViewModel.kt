package br.com.dionataferraz.vendas.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.login.data.repository.LoginRepository
import br.com.dionataferraz.vendas.login.domain.usecase.GetLoginUsecase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val usecase by lazy {
        GetLoginUsecase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    fun login(email: String?, password: String?) {
        viewModelScope.launch {
            if (email != null && password != null) {
                val user = usecase.login(email = email, password = password)
                Log.e("login", user.get().toString())
            } else {
                error.value = true
            }
        }
    }
}