package com.example.collpage.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.HomeViewModel
import com.example.collpage.R
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navigateToWelcome: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopAppBar {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            Button(
                onClick = {
                    Firebase.auth.signOut()
                    navigateToWelcome()
                },
                shape = RoundedCornerShape(17.dp),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Log Out", fontFamily = Poppins, fontWeight = FontWeight.SemiBold)
            }
        }
    ) {
        Column(Modifier.padding(it)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    Modifier
                        .width(335.dp)
                        .height(60.dp)
                        .background(
                            Color(0xFFD9D9D9), RoundedCornerShape(35.dp)
                        )
                        .clickable { }
                ) {
                    Icon(
                        painterResource(R.drawable.search),
                        null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(top = 12.dp, start = 25.dp)
                            .size(35.dp)
                    )
                    Text(
                        "Search...",
                        fontFamily = Poppins,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 16.dp),
                        color = Color(0xFF909090)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeTopAppBar(onNavIconClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.padding(start = 10.dp, top = 15.dp)) {
            IconButton(onClick = onNavIconClick) {
                Icon(painterResource(R.drawable.nav_drawer), null)
            }
        }
        Row(Modifier.padding(end = 12.dp, top = 20.dp)) {
            Row(
                modifier = Modifier
                    .padding(end = 18.dp)
                    .background(
                        Color(0xFFD9D9D9), RoundedCornerShape(30.dp)
                    )
                    .clickable { }
            ) {
                Icon(
                    painterResource(R.drawable.location),
                    null,
                    tint = Color(0xFF1C6973),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(vertical = 8.dp)
                )
                Text(
                    "Malang",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, end = 10.dp),
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Icon(
                    painterResource(R.drawable.arrow_down),
                    null,
                    Modifier
                        .size(32.dp)
                        .padding(top = 11.dp, end = 12.dp)
                )
            }
            Column(Modifier.padding(top = 5.dp)) {
                IconButton(onClick = { /*TODO*/ }, Modifier.size(34.dp)) {
                    Icon(
                        painterResource(R.drawable.notification_bell), null,
                        Modifier.size(34.dp), Color.Black
                    )
                }
            }
        }
    }
}