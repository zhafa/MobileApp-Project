package com.example.mobileapptechnobit.viewModel

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapptechnobit.HomeActivity
import com.example.mobileapptechnobit.data.remote.ApiClient
import kotlinx.coroutines.launch
import org.json.JSONObject

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    init {
        ApiClient.initialize(application.applicationContext)
    }

    fun performLogin(email: String, password: String) {
        viewModelScope.launch {
            ApiClient.login(email, password, { response ->
                handleLoginSuccess(response)
            }, { error ->
                handleLoginError(error)
            })
        }
    }

    private fun handleLoginSuccess(response: JSONObject) {
        val status = response.getInt("status")
        val message = response.getString("message")

        if (status == 200 && message == "Login berhasil") {
            Toast.makeText(getApplication(), "Login Berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(getApplication(), HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            getApplication<Application>().startActivity(intent)
        } else {
            Toast.makeText(getApplication(), "Login Gagal: $message", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleLoginError(error: String) {
        Toast.makeText(getApplication(), "Error: $error", Toast.LENGTH_SHORT).show()
    }
}