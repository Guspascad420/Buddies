package com.example.collpage.ui.screens.user_fields.educations

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
import com.example.collpage.ui.screens.EducationSection
import com.example.collpage.ui.theme.Poppins

@Composable
fun EducationScreen(viewModel: MainViewModel, navigateToAdd: () -> Unit) {
    Column {
        Surface(Modifier.fillMaxWidth(), color = Color(0xFF1C6973)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 25.dp),
                Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(R.drawable.back_arrow), null,
                        tint = Color(0xFFE5E8CD)
                    )
                }
                Text(
                    "Pendidikan", fontWeight = FontWeight.SemiBold,
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
        viewModel.userEducations.forEach {
            EducationSection(it, true)
            Divider(Modifier.padding(top = 4.dp), thickness = 1.dp)
        }
    }
}