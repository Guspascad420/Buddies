package com.example.collpage.ui.screens.user_fields.educations

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
import com.example.collpage.ui.screens.user_fields.project.YearSheet
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEducation(mainViewModel: MainViewModel, ufvm: UserFieldViewModel = viewModel()) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    var sheetContent by remember { mutableStateOf(SheetContent.START_MONTHS) }
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            YearSheet(sheetContent, ufvm) {
                scope.launch {
                    sheetState.hide()
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
                        .padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Tambah Pendidikan",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        color = Color(0xFFE5E8CD)
                    )
                }
            }
            Spacer(Modifier.height(25.dp))
            Column(Modifier.padding(horizontal = 15.dp)) {
                Text("Nama Instansi", fontFamily = Poppins, fontWeight = FontWeight.Medium)
                TextField(
                    value = ufvm.almamaterName,
                    onValueChange = { ufvm.almamaterName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Spacer(Modifier.height(25.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Column {
                        Text("Tahun Masuk", fontFamily = Poppins, fontWeight = FontWeight.Medium)
                        Row(
                            Modifier
                                .width(120.dp)
                                .padding(top = 5.dp)
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
                                ufvm.yearIn,
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
                    Column {
                        Text("Tahun Lulus", fontFamily = Poppins, fontWeight = FontWeight.Medium)
                        Row(
                            Modifier
                                .width(120.dp)
                                .padding(top = 5.dp)
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
                                ufvm.yearOut,
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
                }
                Spacer(Modifier.height(25.dp))
                Text("Bidang", fontFamily = Poppins, fontWeight = FontWeight.Medium)
                Surface(Modifier.fillMaxWidth(), RoundedCornerShape(15.dp), getInputColor()) {
                    Row(Modifier.fillMaxWidth().padding(10.dp), Arrangement.SpaceBetween) {
                        Text(ufvm.major, fontFamily = Poppins, fontWeight = FontWeight.Medium)
                        Icon(
                            painterResource(R.drawable.arrow_down), null,
                            Modifier.padding(top = 11.dp, end = 5.dp)
                        )
                    }
                }
                Spacer(Modifier.height(25.dp))
                Text("Aktivitas dan Kegiatan", fontFamily = Poppins, fontWeight = FontWeight.Medium)
                OutlinedTextField(
                    value = ufvm.eduActivity,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { ufvm.eduActivity = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(180.dp)
                )
                Button(
                    onClick = { /*TODO*/ },
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