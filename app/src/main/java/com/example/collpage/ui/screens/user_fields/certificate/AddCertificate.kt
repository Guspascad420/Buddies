package com.example.collpage.ui.screens.user_fields.certificate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.SheetContent
import com.example.collpage.ui.UserFieldViewModel
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddCertiificate(mainViewModel: MainViewModel, ufvm: UserFieldViewModel = viewModel()){
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var sheetContent by remember { mutableStateOf(SheetContent.START_MONTHS) }
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            if (sheetContent == SheetContent.START_MONTHS || sheetContent == SheetContent.END_MONTHS) {
                com.example.collpage.ui.screens.user_fields.project.MonthSheet(
                    sheetContent,
                    mainViewModel.months,
                    ufvm
                ) {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            } else if (sheetContent == SheetContent.START_YEARS || sheetContent == SheetContent.END_YEARS) {
                com.example.collpage.ui.screens.user_fields.project.YearSheet(
                    sheetContent,
                    ufvm
                ) {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            }
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Column {
            Surface(
                Modifier
                    .fillMaxWidth(), color = Color(0xFF1C6973)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Tambah Project",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        color = Color(0xFFE5E8CD)
                    )
                }
            }
            Spacer(Modifier.height(25.dp))
            Column(Modifier.padding(horizontal = 15.dp)) {
                Text(
                    "Nama Sertifikasi",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "Penyelenggara",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "Tanggal Penerbitan",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        Modifier
                            .width(170.dp)
                            .background(getInputColor(), RoundedCornerShape(8.dp))
                            .clickable {
                                sheetContent = SheetContent.START_MONTHS
                                scope.launch {
                                    if (sheetState.isVisible) sheetState.hide()
                                    else sheetState.show()
                                }
                            },
                        Arrangement.SpaceBetween
                    ) {
                        Text(
                            ufvm.startMonth,
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            modifier = Modifier.padding(5.dp)
                        )
                        Icon(
                            painterResource(R.drawable.arrow_down), null,
                            Modifier.padding(top = 11.dp, end = 5.dp)
                        )
                    }
                    Row(
                        Modifier
                            .width(170.dp)
                            .background(getInputColor(), RoundedCornerShape(8.dp))
                            .clickable {
                                sheetContent = SheetContent.START_YEARS
                                scope.launch {
                                    if (sheetState.isVisible) sheetState.hide()
                                    else sheetState.show()
                                }
                            },
                        Arrangement.SpaceBetween
                    ) {
                        Text(
                            ufvm.startYear,
                            fontSize = 12.sp,
                            fontFamily = Poppins,
                            modifier = Modifier.padding(5.dp)
                        )
                        Icon(
                            painterResource(R.drawable.arrow_down), null,
                            Modifier.padding(top = 11.dp, end = 5.dp)
                        )
                    }
                }
                Spacer(Modifier.height(15.dp))
                Text(
                    "Link Sertifikasi",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                OutlinedTextField(value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = getInputColor(),
                        unfocusedBorderColor = Color.Transparent,
                        focusedBorderColor = Color(0xFF1C6973),
                        cursorColor = Color(0xFF1C6973)
                    ),
                    modifier = Modifier
                        .width(320.dp)
                        .padding(top = 10.dp)
                )
                Spacer(Modifier.height(15.dp))
                Button(
                    onClick = { },
                    Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF1C6973)
                    )
                ) {
                    Text("Simpan", fontFamily = Poppins, color = Color.White)
                }
            }
        }
    }
}
