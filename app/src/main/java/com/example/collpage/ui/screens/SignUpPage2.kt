package com.example.collpage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.AuthUiState
import com.example.collpage.ui.AuthViewModel
import com.example.collpage.ui.theme.Poppins

@Composable
fun SignUpPage2(
    viewModel: AuthViewModel,
    navigateToHome: () -> Unit
) {
    val acceptTermsIcon = if (viewModel.rememberMe) R.drawable.vector_2 else R.drawable.vector
    val tint = if (viewModel.rememberMe) Color(0xFF1C6973) else Color(0xFF909090)
    val passwordIconRes = if (viewModel.isPasswordVisible) R.drawable.mdi_eye_on
    else R.drawable.mdi_eye_off
    val visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None
    else PasswordVisualTransformation()
    val passwordIcon = @Composable {
        IconButton(
            onClick = { viewModel.isPasswordVisible = !viewModel.isPasswordVisible },
            Modifier.size(34.dp)
        ) {
            Icon(painterResource(passwordIconRes), null)
        }
    }

    LaunchedEffect(key1 = viewModel.authUiState) {
        if (viewModel.authUiState == AuthUiState.Success) {
            navigateToHome()
        }
    }
    val acceptTermsChecked = viewModel.rememberMe

    Column {
        Box {
            Image(painterResource(R.drawable.vector_3), null, Modifier.padding(top = 22.dp))
            Image(
                painterResource(R.drawable.vector_6), null
            )
            Column(Modifier.padding(top = 45.dp, start = 35.dp)) {
                Text(
                    "Selamat Datang", fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold, fontSize = 37.sp,
                    color = Color(0xFFE5E8CD)
                )
                Text(
                    "Silahkan buat akun baru!", fontFamily = Poppins,
                    fontWeight = FontWeight.Light, fontSize = 20.sp,
                    color = Color(0xFFE5E8CD)
                )
            }
        }
        Column(
            Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.authUiState == AuthUiState.Error) {
                Text(
                    "Tolong masukkan password yang sama",
                    color = Color(0xFFE93030),
                    modifier = Modifier.padding(end = 5.dp),
                    fontSize = 12.sp
                )
            }
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF1C6973),
                    cursorColor = Color(0xFF1C6973)
                ),
                placeholder = {
                    Text(
                        "Password",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF909090)
                    )
                },
                visualTransformation = visualTransformation,
                modifier = Modifier.width(320.dp),
                trailingIcon = passwordIcon
            )
            Text(
                "*Buatlah password minimal 8 karakter, dengan kombinasi huruf KAPITAL, angka, & tanda baca.",
                fontFamily = Poppins,
                fontWeight = FontWeight.ExtraLight,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(bottom = 14.dp, start = 5.dp)
                    .width(320.dp)
            )
            OutlinedTextField(
                value = viewModel.passwordConfirm,
                onValueChange = { viewModel.passwordConfirm = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF1C6973),
                    cursorColor = Color(0xFF1C6973)
                ),
                placeholder = {
                    Text(
                        "Confirm Password",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF909090)
                    )
                },
                visualTransformation = visualTransformation,
                modifier = Modifier.width(320.dp),
                trailingIcon = passwordIcon
            )
            Text(
                "*Masukkan kembali password yang telah dibuat, " +
                        "pastikan memasukkan password yang sama.",
                fontFamily = Poppins,
                fontWeight = FontWeight.ExtraLight,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(bottom = 14.dp, start = 5.dp)
                    .width(320.dp)
            )
            Row(Modifier.padding(8.dp)) {
                IconButton(
                    onClick = { viewModel.rememberMe = !viewModel.rememberMe },
                    Modifier.size(16.dp)
                ) {
                    Icon(
                        painterResource(acceptTermsIcon), null,
                        Modifier.size(16.dp), tint = tint
                    )
                }
                Text(
                    "Saya menyetujui ",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp),
                )
                ClickableText(
                    text = AnnotatedString("syarat & ketentuan "),
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.sp,
                        color = Color(0xFF1C6973)
                    ),
                    onClick = { viewModel.openTCDialog = true }
                )
                Text(
                    "yang berlaku",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp,
                )
            }
            Button(
                onClick = { viewModel.handleSignup() },
                enabled = viewModel.password.text != "" && viewModel.passwordConfirm.text != ""
                        && acceptTermsChecked,
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF1C6973)),
                modifier = Modifier
                    .width(330.dp)
                    .padding(top = 25.dp)
            ) {
                Text(
                    "Sign Up",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
        if (viewModel.openTCDialog) {
            TermsConditionsDialog {
                viewModel.openTCDialog = false
            }
        }
    }
}

@Composable
fun TermsConditionsDialog(
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 30.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                Modifier
                    .padding(top = 23.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Syarat & Ketentuan",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
                Text(
                    "Sebelum melangkah lebih jauh, luangkan waktu anda " +
                            "untuk membaca dan menyetujui hal-hal berikut :",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 17.dp)
                )
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    RoundedCornerShape(14.dp),
                    getInputColor()
                ) {
                    Column(Modifier.padding(start = 8.dp, bottom = 5.dp)) {
                        Text(
                            "1. Registrasi Akun:",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                        Text(
                            stringResource(R.string.register_tc),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "2. Penggunaan Aplikasi:",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                        Text(
                            stringResource(R.string.app_usage_tc),
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    }
                }
                TextButton(
                    onDismiss,
                    Modifier.padding(vertical = 3.dp)
                ) {
                    Text(
                        "Tutup", color = Color(0xFF1C6973), fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold, fontFamily = Poppins
                    )
                }
            }
        }
    }
}