package com.example.collpage.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.SearchViewModel
import com.example.collpage.ui.theme.Poppins

@Composable
fun SearchPage(
    viewModel: SearchViewModel = viewModel(),
    navigateToFilter: () -> Unit
) {
    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 20.dp, bottom = 12.dp)) {
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            Box(Modifier.padding(top = 7.dp)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(R.drawable.back_arrow), null)
                }
            }
            OutlinedTextField(
                viewModel.query,
                { viewModel.query = it },
                Modifier
                    .width(280.dp)
                    .padding(end = 10.dp),
                shape = RoundedCornerShape(35.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF1C6973),
                    cursorColor = Color(0xFF1C6973)
                ),
                trailingIcon = {
                    Icon(painterResource(R.drawable.search), null)
                },
                placeholder = {
                    Text("Search...", fontFamily = Poppins, color = Color(0xFF909090))
                }
            )
            Surface(Modifier.padding(top = 7.dp), CircleShape, Color(0xFF1C6973)) {
                IconButton(onClick = navigateToFilter) {
                    Icon(painterResource(R.drawable.filter), null, tint = Color.White)
                }
            }
        }
        Spacer(Modifier.height(15.dp))
        Text("Riwayat", Modifier.padding(start = 10.dp), fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold, fontSize = 24.sp)
        SearchResult(viewModel = viewModel)
    }
}

@Composable
fun SearchResult(viewModel: SearchViewModel) {
    val resultTypeList = listOf("Semua", "Akun", "Pekerjaan", "Kursus")
    LazyColumn() {
        item {
            LazyRow {
                items(resultTypeList) { type ->
                    val borderWidth = if (viewModel.activeResultType == type) 1.dp else 0.dp
                    val bgColor = if (viewModel.activeResultType == type) Color(0xFF1C6973)
                    else MaterialTheme.colors.background
                    Button(
                        onClick = {  },
                        Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bgColor
                        ),
                        border = BorderStroke(borderWidth, MaterialTheme.colors.onSurface),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            type, Modifier.padding(5.dp),
                            fontFamily = Poppins
                        )
                    }
                }
            }
        }
        item {
            CardResult()
        }
    }
}

@Composable
fun CardResult() {
    Card(Modifier.padding(10.dp), RoundedCornerShape(18.dp)) {
        Column(Modifier.padding(20.dp)) {
            Row {
                Image(
                    painterResource(R.drawable.figmavektor), null,
                    Modifier.padding(top = 5.dp, end = 8.dp)
                )
                Column {
                    Text(
                        "UI/UX Design with Figma",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Progate",
                        Modifier.padding(horizontal = 20.dp),
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF909090)
                    )
                    //ini gimana buat yang nilainya
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 7.dp))
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Row {
                    Image(
                        painterResource(R.drawable.rp), null,
                        Modifier.padding(top = 5.dp, end = 5.dp)
                    )
                    Text(
                        "Rp. 150.000",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF1C6973)),
                    modifier = Modifier
                ) {
                    Text(
                        "Detail",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}