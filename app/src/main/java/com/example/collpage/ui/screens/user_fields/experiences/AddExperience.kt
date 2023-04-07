package com.example.collpage.ui.screens.user_fields.experiences

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
import com.example.collpage.ui.FieldUiState
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.SheetContent
import com.example.collpage.ui.UserFieldViewModel
import com.example.collpage.ui.screens.user_fields.project.MonthSheet
import com.example.collpage.ui.screens.user_fields.project.YearSheet
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddExperience(
    mainViewModel: MainViewModel,
    ufvm: UserFieldViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var sheetContent by remember { mutableStateOf(SheetContent.START_MONTHS) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(mainViewModel.fieldUiState) {
        if (mainViewModel.fieldUiState == FieldUiState.Success) {
            navigateBack()
        }
    }

    ModalBottomSheetLayout(
        sheetContent = {
            if (sheetContent == SheetContent.START_MONTHS || sheetContent == SheetContent.END_MONTHS) {
                MonthSheet(sheetContent, mainViewModel.months, ufvm) {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            } else if (sheetContent == SheetContent.START_YEARS || sheetContent == SheetContent.END_YEARS) {
                YearSheet(sheetContent, ufvm) {
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
            Surface(Modifier.fillMaxWidth(), color = Color(0xFF1C6973)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp), Arrangement.Start) {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            painterResource(R.drawable.back_arrow), null,
                            Modifier, Color(0xFFE5E8CD)
                        )
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp), Arrangement.Center) {
                    Text(
                        "Tambah Pengalaman",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins,
                        fontSize = 24.sp
                    )
                }
            }
            Column(Modifier.padding(horizontal = 15.dp)) {
                Text("Nama Kegiatan", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                TextField(
                    value = ufvm.experienceName,
                    onValueChange = { ufvm.experienceName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Text("Posisi", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                TextField(
                    value = ufvm.position,
                    onValueChange = { ufvm.position = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Text("Tanggal Mulai", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        Modifier
                            .width(170.dp)
                            .background(getInputColor(), RoundedCornerShape(8.dp))
                            .clickable {
                                sheetContent = SheetContent.END_MONTHS
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
                Text("Tanggal Selesai", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(
                        Modifier
                            .width(170.dp)
                            .background(getInputColor(), RoundedCornerShape(8.dp))
                            .clickable {
                                sheetContent = SheetContent.END_MONTHS
                                scope.launch {
                                    if (sheetState.isVisible) sheetState.hide()
                                    else sheetState.show()
                                }
                            },
                        Arrangement.SpaceBetween
                    ) {
                        Text(
                            ufvm.endMonth,
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
                                sheetContent = SheetContent.END_YEARS
                                scope.launch {
                                    if (sheetState.isVisible) sheetState.hide()
                                    else sheetState.show()
                                }
                            },
                        Arrangement.SpaceBetween
                    ) {
                        Text(
                            ufvm.endYear,
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
                Text("Deskripsi", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                OutlinedTextField(
                    value = ufvm.description,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(180.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(25.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF1C6973)),
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Simpan", fontFamily = Poppins, color = Color.White)
                    }
                }
            }
        }
    }
}