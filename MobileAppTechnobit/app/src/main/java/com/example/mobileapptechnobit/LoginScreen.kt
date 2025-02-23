package com.example.mobileapptechnobit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import com.example.mobileapptechnobit.ui.theme.primary100
import com.example.mobileapptechnobit.ui.theme.robotoFontFamily

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
        ){
            Column (
                modifier = Modifier .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Masuk",
                    fontSize = 22.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(13.dp))

                Text(text = "Masuk ke akun anda selanjutnya",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal)
            }

            Column (
                modifier = Modifier .padding(top = 40.dp, start = 40.dp)
            ){
                Text(text = "Email",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier .padding(bottom = 5.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Masukkan email anda") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 40.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFA9A9A9),
                        unfocusedBorderColor = Color(0xFFA9A9A9),
                        cursorColor = Color.Black
                    )

                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Password",
                    fontSize = 16.sp,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier .padding(bottom = 5.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Masukkan password anda") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
//                        Text(
//                            text = if (passwordVisible) "🙈" else "👁",
//                            modifier = Modifier.padding(end = 8.dp),
//                            color = Color.Gray
//                        )
                        Image(
                            painter = painterResource(
                                id = if (passwordVisible) R.drawable.eyes_open else R.drawable.eyes_open
                            ),
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { passwordVisible = !passwordVisible }
                                .padding(end = 8.dp)
                        )
                    },

                    modifier = Modifier.fillMaxWidth() .padding(end = 40.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFA9A9A9),
                        unfocusedBorderColor = Color(0xFFA9A9A9),
                        cursorColor = Color.Black
                    )
                )
                Text(
                    text = "Ubah Password",
                    textDecoration = TextDecoration.Underline,
                    color = primary100,
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
//                        .clickable {
//                            val intent = Intent(context, ResetPasswordActivity::class.java)
//                            context.startActivity(intent)
//                        }
                        .align(Alignment.End)
                        .padding(40.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { /* TODO: Handle login */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3366FF)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(end = 40.dp)
                ) {
                    Text(text = "Masuk", fontSize = 16.sp, color = Color.White)
                }
            }

        }
    }
}


@Preview (showBackground = true, device = "spec:width=412dp,height=915dp,dpi=440")
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}