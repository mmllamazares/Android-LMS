package com.example.myapplication.userName

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserNameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val userName: LiveData<UserEntity>

    init {
        val dao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(dao)
        userName = repository.user
    }

    fun setUserName(name: String) {
        viewModelScope.launch {
            repository.saveUser(name)
        }
    }

    fun updateUserName(name: String) {
        viewModelScope.launch {
            // Necesitamos el id del usuario actual para actualizarlo
            val currentId = userName.value?.id ?: return@launch
            repository.updateUser(name, currentId)
        }
    }

//    private val _userName = MutableLiveData<String>()
//    val userName: LiveData<String> = _userName
//
//    fun setUserName(name: String) {
//        _userName.value = name
//    }
}