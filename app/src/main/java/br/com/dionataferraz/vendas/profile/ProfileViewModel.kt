package br.com.dionataferraz.vendas.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.login.data.request.NewUserRequest
import br.com.dionataferraz.vendas.login.domain.usecase.GetLoginUsecase
import br.com.dionataferraz.vendas.profile.domain.usecase.RegisterUserUsecase
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel()  {

    private val usecase by lazy {
        RegisterUserUsecase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error

    private val login: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowLogin: LiveData<Boolean> = login

    fun register(newUserRequest: NewUserRequest) {
        viewModelScope.launch {
            if (!newUserRequest.email.isBlank() &&
                !newUserRequest.password.isBlank() &&
                !newUserRequest.name.isBlank()) {
                val user = usecase.regster(newUserRequest)

                if (user.get() != null) {
                    login.value = true
                } else {
                    error.value = true
                }
                Log.e("login", user.get().toString())
            } else {
                error.value = true
            }
        }
    }

}