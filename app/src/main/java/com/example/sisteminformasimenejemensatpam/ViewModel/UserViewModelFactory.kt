package com.example.sisteminformasimenejemensatpam.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sisteminformasimenejemensatpam.data.repository.KaryawanRepository
import com.example.sisteminformasimenejemensatpam.data.repository.UserRepository

class UserViewModelFactory (private val repository: UserRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}