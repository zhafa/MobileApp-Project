package com.example.mobileapptechnobit

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Masuk", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(13.dp))
                Text("Masuk ke akun anda selanjutnya", fontSize = 16.sp)
            }

            Column(
                modifier = Modifier.padding(top = 40.dp, start = 40.dp)
            ) {
                Text("Email", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Masukkan email anda") },
                    modifier = Modifier.fillMaxWidth().padding(end = 40.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text("Password", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Masukkan password anda") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = if (passwordVisible) R.drawable.eyes_open else R.drawable.eyes_open),
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { passwordVisible = !passwordVisible }
                        )
                    },
                    modifier = Modifier.fillMaxWidth().padding(end = 40.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { performLogin(context, email, password) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3366FF)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp).padding(end = 40.dp)
                ) {
                    Text("Masuk", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}

fun performLogin(context: Context, email: String, password: String) {
    if (email.isEmpty() || password.isEmpty()) {
        Toast.makeText(context, "Harap isi semua bidang!", Toast.LENGTH_SHORT).show()
        return
    }

    val url = "${DbContract.urlLogin}?email=$email&password=$password"
    Log.d("LoginDebug", "Requesting URL: $url")

    val stringRequest = StringRequest(
        Request.Method.GET, url,
        { response ->
            Log.d("LoginDebug", "Response: $response")
            if (response.trim().lowercase() == "login successful") {
                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, HomeActivity::class.java))
            } else {
                Toast.makeText(context, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        },
        { error ->
            Log.e("LoginDebug", "Error: ${error.message}")
            Toast.makeText(context, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    )

    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    requestQueue.add(stringRequest)
}
