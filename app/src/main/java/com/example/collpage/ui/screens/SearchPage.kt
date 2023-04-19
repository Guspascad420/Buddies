package com.example.collpage.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.Job
import com.example.collpage.ui.SearchUiState
import com.example.collpage.ui.SearchViewModel
import com.example.collpage.ui.theme.Poppins

@Composable
fun SearchPage(
    viewModel: SearchViewModel,
    navigateToFilter: () -> Unit,
    navigateToJobDetails: (String) -> Unit
) {
    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 20.dp, bottom = 12.dp)
    ) {
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
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { viewModel.getJobResults() }
                )
            )
            Surface(Modifier.padding(top = 7.dp), CircleShape, Color(0xFF1C6973)) {
                IconButton(onClick = navigateToFilter) {
                    Icon(painterResource(R.drawable.filter), null, tint = Color.White)
                }
            }
        }
        Spacer(Modifier.height(15.dp))
        if (viewModel.searchUiState == SearchUiState.Success) {
            Text(
                "Hasil Pencarian", Modifier.padding(start = 10.dp), fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold, fontSize = 24.sp
            )
            SearchResult(viewModel = viewModel, navigateToJobDetails)
        } else {
            Text(
                "Riwayat", Modifier.padding(start = 10.dp), fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold, fontSize = 24.sp
            )
        }
    }
}

@Composable
fun SearchResult(viewModel: SearchViewModel, navigateToJobDetails: (String) -> Unit) {
    val resultTypeList = listOf("Semua", "Akun", "Pekerjaan", "Kursus")
    LazyColumn {
        item {
            LazyRow {
                items(resultTypeList) { type ->
                    val borderWidth = if (viewModel.activeResultType == type) 1.dp else 0.dp
                    val bgColor = if (viewModel.activeResultType == type) Color(0xFF1C6973)
                    else MaterialTheme.colors.background
                    val textColor = if (viewModel.activeResultType == type) Color.White
                                    else MaterialTheme.colors.onSurface
                    Button(
                        onClick = { },
                        Modifier.padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = bgColor
                        ),
                        border = BorderStroke(borderWidth, MaterialTheme.colors.onSurface),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            type, Modifier.padding(5.dp),
                            fontFamily = Poppins, color = textColor
                        )
                    }
                }
            }
        }
        items(viewModel.jobsList) {
            CardResult(it, navigateToJobDetails)
        }
    }
}

@Composable
fun CardResult(job: Job, navigateToJobDetails: (String) -> Unit) {
    Card(Modifier.padding(10.dp), RoundedCornerShape(18.dp)) {
        Column(Modifier.padding(20.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Row {
                    Image(
                        painterResource(R.drawable.figmavektor), null,
                        Modifier.padding(top = 5.dp, end = 8.dp)
                    )
                    Column {
                        Text(
                            job.name,
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        job.company?.let { company ->
                            Row {
                                Text(
                                    company.name,
                                    fontFamily = Poppins,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Light,
                                    color = Color(0xFF909090)
                                )
                                if (company.is_verified) {
                                    Icon(painterResource(R.drawable.verified), null,
                                        Modifier.padding(top = 8.dp, start = 5.dp), tint = Color(0xFF1C6973))
                                }
                            }
                        }
                    }
                }
                Icon(painterResource(R.drawable.three_dots), null)
            }
            Row(Modifier.padding(start = 10.dp)) {
                Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFD9D9D9)) {
                    Text(job.placement, Modifier.padding(5.dp),
                        fontFamily = Poppins, color = Color(0xFF696969))
                }
            }
            Divider(Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 12.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Row(Modifier.padding(top = 10.dp)) {
                    Image(
                        painterResource(R.drawable.rp), null,
                        Modifier.padding(top = 5.dp, end = 5.dp)
                    )
                    Text(
                        job.salary_range,
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text("/Bulan", fontFamily = Poppins, color = Color(0xFF909090))
                }
                Button(
                    onClick = { navigateToJobDetails(job.id) },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF1C6973))
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