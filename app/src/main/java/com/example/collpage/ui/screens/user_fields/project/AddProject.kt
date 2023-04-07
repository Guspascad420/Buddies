package com.example.collpage.ui.screens.user_fields.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.*
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddProject(
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
                Row(Modifier.fillMaxWidth().padding(top = 20.dp), Arrangement.Start) {
                    IconButton(onClick = navigateBack) {
                        Icon(painterResource(R.drawable.back_arrow), null,
                            Modifier, Color(0xFFE5E8CD))
                    }
                }
                Row(Modifier.fillMaxWidth().padding(vertical = 20.dp), Arrangement.Center) {
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
                    "Judul",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                TextField(
                    value = ufvm.projectTitle,
                    onValueChange = { ufvm.projectTitle = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "Tipe Project",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    fontFamily = Poppins
                )
                TextField(
                    value = ufvm.projectType,
                    onValueChange = { ufvm.projectType = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background
                    )
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    "Anggota Tim",
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
                    "Tanggal Mulai",
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
                    "Tanggal Selesai",
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
                Spacer(Modifier.height(15.dp))
                Text("Deskripsi", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                OutlinedTextField(
                    value = ufvm.description,
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { ufvm.description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(180.dp)
                )
                Button(
                    onClick = {
                        val newProjectMap = hashMapOf(
                            "name" to ufvm.projectTitle.text,
                            "type" to ufvm.projectType.text,
                            "start_date" to "${ufvm.startMonth} ${ufvm.startYear}",
                            "end_date" to "${ufvm.endMonth} ${ufvm.endYear}",
                            "description" to ufvm.description.text
                        )
                        val newProjectInstance = Project(
                            ufvm.projectTitle.text,
                            ufvm.description.text,
                            "${ufvm.startMonth} ${ufvm.startYear}",
                            "${ufvm.endMonth} ${ufvm.endYear}",
                            ufvm.description.text
                        )
                        mainViewModel.addUserProject(newProjectMap, newProjectInstance)
                    },
                    Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1C6973))
                ) {
                    Text("Simpan", fontFamily = Poppins, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MonthSheet(
    sheetContent: SheetContent,
    months: List<String>,
    ufvm: UserFieldViewModel,
    hideSheetState: () -> Unit
) {
    val onMonthClick: (String) -> Unit = { month ->
        if (sheetContent == SheetContent.START_MONTHS) {
            ufvm.startMonth = month
        } else {
            ufvm.endMonth = month
        }
    }
    months.forEach { month ->
        ClickableText(
            AnnotatedString(month),
            Modifier
                .fillMaxWidth()
                .padding(18.dp),
            TextStyle(fontFamily = Poppins, color = MaterialTheme.colors.onSurface),
            onClick = {
                onMonthClick(month)
                hideSheetState()
            },
        )
        Divider(Modifier.fillMaxWidth(), thickness = 1.dp)
    }
}

@Composable
fun YearSheet(sheetContent: SheetContent, ufvm: UserFieldViewModel, hideSheetState: () -> Unit) {
    val onYearClick: (String) -> Unit = { year ->
        if (sheetContent == SheetContent.START_YEARS) {
            ufvm.startYear = year
        } else {
            ufvm.endYear = year
        }
    }
    val startYear = if (sheetContent == SheetContent.START_YEARS) 1990 else ufvm.startYear.toInt() + 1
    Column(Modifier.verticalScroll(rememberScrollState())) {
        for (i in startYear..2030) {
            ClickableText(
                AnnotatedString("$i"),
                Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                TextStyle(fontFamily = Poppins, color = MaterialTheme.colors.onSurface),
                onClick = {
                    onYearClick("$i")
                    hideSheetState()
                },
            )
            Divider(Modifier.fillMaxWidth(), thickness = 1.dp)
        }
    }
}