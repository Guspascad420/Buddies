package com.example.collpage.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.theme.Poppins

@Composable
fun EditProfile(mainViewModel: MainViewModel) {
    var fullName by remember { mutableStateOf(TextFieldValue(mainViewModel.user.name)) }
    var placeOfBirth by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf("Laki-laki") }
    var district by remember { mutableStateOf("") }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    var religion by remember { mutableStateOf("Agama") }

    val maleButtonColor = if (gender == "Laki-laki") Color(0xFF1C6973) else Color.White
    val maleButtonTextColor = if (gender == "Laki-laki") Color.White else Color.Black
    val femaleButtonColor = if (gender == "Perempuan") Color(0xFF1C6973) else Color.White
    val femaleButtonTextColor = if (gender == "Perempuan") Color.White else Color.Black

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Box {
            Surface(
                Modifier
                    .height(130.dp)
                    .fillMaxWidth(), color = Color(0xFF1C6973)
            ) { }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(R.drawable.monke_2),
                    null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(130.dp)
                )
            }
        }
        Column(Modifier.padding(15.dp)) {
            Text(
                "Biodata",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                "Nama Lengkap*",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.width(320.dp).padding(top = 5.dp)
            )
            Text(
                "Tempat, Tanggal Lahir*",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "Jenis Kelamin*",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Row {
                Button(
                    onClick = { gender = "Laki-laki" },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = maleButtonColor
                    ),
                    border = BorderStroke(2.dp, Color(0xFF1C6973)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        "Laki-laki", Modifier.padding(5.dp),
                        fontFamily = Poppins, color = maleButtonTextColor
                    )
                }
                Divider(Modifier.width(10.dp), MaterialTheme.colors.background)
                Button(
                    onClick = { gender = "Perempuan" },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = femaleButtonColor
                    ),
                    border = BorderStroke(2.dp, Color(0xFF1C6973)),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        "Perempuan", Modifier.padding(5.dp),
                        fontFamily = Poppins, color = femaleButtonTextColor
                    )
                }
            }
            Text(
                "Alamat*",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = getInputColor(),
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(190.dp)
                        .padding(top = 15.dp)
                )
                Divider(Modifier.width(20.dp), MaterialTheme.colors.background)
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = getInputColor(),
                        unfocusedBorderColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 15.dp)
                )
            }
            OutlinedTextField(
                value = "",
                onValueChange = {},
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = getInputColor(),
                    unfocusedBorderColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            )
            Text(
                "Agama",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Surface(
                Modifier
                    .width(250.dp)
                    .clickable { },
                RoundedCornerShape(15.dp),
                getInputColor()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp), Arrangement.SpaceBetween
                ) {
                    Text(religion, fontFamily = Poppins)
                    Icon(
                        painterResource(R.drawable.arrow_down), null,
                        Modifier.padding(top = 10.dp)
                    )
                }
            }
            Button(
                onClick = { },
                Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1C6973)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Simpan", fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
            }
        }
    }
}