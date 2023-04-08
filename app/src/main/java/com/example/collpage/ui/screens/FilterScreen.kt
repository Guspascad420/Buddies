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
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterScreen() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        { Text("") }, sheetState = sheetState,
        sheetShape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column(
            Modifier
                .padding(horizontal = 22.dp)
                .padding(top = 20.dp)) {
            Box {
                Row(Modifier.fillMaxWidth(), Arrangement.Start) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painterResource(R.drawable.back_arrow), null)
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp), Arrangement.Center) {
                    Text(
                        "Filter", fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold, fontSize = 24.sp
                    )
                }
            }
            Column(Modifier.padding(top = 12.dp)) {
                Text(
                    "Kategori", fontFamily = Poppins,
                    fontWeight = FontWeight.Medium, fontSize = 20.sp
                )
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                if (sheetState.isVisible) sheetState.hide()
                                else sheetState.show()
                            }
                        },
                    RoundedCornerShape(15.dp),
                    getInputColor()
                ) {
                    Row(
                        Modifier
                            .padding(15.dp)
                            .fillMaxWidth(), Arrangement.SpaceBetween) {
                        Row {
                            Icon(
                                painterResource(R.drawable.arts_crafts),
                                null,
                                Modifier.padding(top = 2.dp, end = 5.dp),
                                Color(0xFF909090)
                            )
                            Text(
                                "Desainer", fontFamily = Poppins,
                                fontWeight = FontWeight.Medium, fontSize = 17.sp
                            )
                        }
                        Icon(
                            painterResource(R.drawable.arrow_down),
                            null,
                            Modifier
                                .padding(top = 3.dp)
                                .size(24.dp),
                            Color(0xFF909090)
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "Sub Kategori", fontFamily = Poppins,
                    fontWeight = FontWeight.Medium, fontSize = 20.sp
                )
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            scope.launch {
                                if (sheetState.isVisible) sheetState.hide()
                                else sheetState.show()
                            }
                        },
                    RoundedCornerShape(15.dp),
                    getInputColor()
                ) {
                    Row(
                        Modifier
                            .padding(15.dp)
                            .fillMaxWidth(), Arrangement.SpaceBetween) {
                        Row {
                            Icon(
                                painterResource(R.drawable.phone),
                                null,
                                Modifier.padding(top = 2.dp, end = 5.dp),
                                Color(0xFF909090)
                            )
                            Text(
                                "UI/UX Desainer", fontFamily = Poppins,
                                fontWeight = FontWeight.Medium, fontSize = 17.sp
                            )
                        }
                        Icon(
                            painterResource(R.drawable.arrow_down),
                            null,
                            Modifier
                                .padding(top = 3.dp)
                                .size(24.dp),
                            Color(0xFF909090)
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = { },
                    Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF1C6973)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Terapkan", fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold, fontSize = 22.sp,
                        color = Color.White)
                }
            }
        }
    }
}