package com.example.collpage.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.collpage.R
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.SheetContent
import com.example.collpage.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessagePage(
    viewModel: MainViewModel,
    navigateToMessage: () -> Unit,
    navigateToHome: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })
    val scope = rememberCoroutineScope()
    viewModel.sheetContent = SheetContent.NOTE

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                {
                    scope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }, Modifier.size(75.dp), backgroundColor = Color(0xFF1C6973)
            ) {
                Icon(
                    painterResource(R.drawable.logo_button),
                    null,
                    Modifier.size(60.dp),
                    Color(0xFFE5E8CD)
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = { BottomBar(viewModel, navigateToMessage, navigateToHome) })
    {
        Column(Modifier.padding(it).padding(15.dp)) {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(color = Color(0xFF97A04A))) {
                        append("Hai, ")
                    }
                    append(viewModel.user.name)
                },
                style = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Medium, fontSize = 22.sp)
            )
            Spacer(Modifier.height(15.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Surface(Modifier.padding(end = 5.dp), CircleShape, getInputColor()) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painterResource(R.drawable.search), null)
                    }
                }
                Row(Modifier.background(getInputColor(), RoundedCornerShape(20.dp))) {
                    TextButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Chat", Modifier.padding(horizontal = 10.dp),
                            fontFamily = Poppins, color = Color(0xFF909090))
                    }
                    TextButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Grup", Modifier.padding(horizontal = 10.dp),
                            fontFamily = Poppins, color = Color(0xFF909090))
                    }
                    TextButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text("Ruang", Modifier.padding(horizontal = 10.dp),
                            fontFamily = Poppins, color = Color(0xFF909090))
                    }
                }
                Surface(Modifier.padding(start = 5.dp), CircleShape, Color(0xFF1C6973)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painterResource(R.drawable.add), null, tint = Color.White)
                    }
                }
            }
        }
    }
}