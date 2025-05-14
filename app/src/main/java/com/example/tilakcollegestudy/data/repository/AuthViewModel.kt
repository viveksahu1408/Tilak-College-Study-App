package com.example.tilakcollegestudy.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository(FirebaseAuth.getInstance())

    private val _loginStatus = MutableLiveData<Result<Boolean>>()
    val loginStatus: LiveData<Result<Boolean>> = _loginStatus

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.loginUser(email, password)
            _loginStatus.value = result
        }
    }



    fun signup(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.signupUser(email, password)
            _loginStatus.value = result
        }
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun logout() {
        repository.logout()
    }
}