package com.example.collpage.ui.screens.user_fields.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.screens.ProjectSection
import com.example.collpage.ui.theme.Poppins

@Composable
fun ProjectScreen(viewModel: MainViewModel, navigateBack: () -> Unit, navigateToAdd: () -> Unit) {
    Column {
        Surface(Modifier.fillMaxWidth(), color = Color(0xFF1C6973)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 25.dp),
                Arrangement.SpaceBetween
            ) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        painterResource(R.drawable.back_arrow), null,
                        tint = Color(0xFFE5E8CD)
                    )
                }
                Text(
                    "Project", fontWeight = FontWeight.SemiBold,
                    fontSize = 26.sp, fontFamily = Poppins, color = Color(0xFFE5E8CD)
                )
                IconButton(onClick = navigateToAdd) {
                    Icon(
                        painterResource(R.drawable.add), null,
                        tint = Color(0xFFE5E8CD)
                    )
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        viewModel.userProjects.forEach {
            ProjectSection(it, true)
            Divider(Modifier.padding(top = 4.dp), thickness = 1.dp)
        }
    }
}