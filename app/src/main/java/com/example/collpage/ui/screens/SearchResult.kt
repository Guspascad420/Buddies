package com.example.collpage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.ui.theme.Poppins

@Composable
fun SearchResult() {
    Column() {
        Card() {
            Row() {
                Image(
                    painterResource(R.drawable.figmavektor), null,
                    Modifier.padding(top = 5.dp)
                )
                Column() {
                    Text(
                        "UI/UX Design with Figma",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Progate",
                        Modifier.padding(horizontal = 20.dp),
                        fontFamily = Poppins,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF909090)
                    )
                    //ini gimana buat yang nilainya
                    Row() {
                        Text("")
                        Image(
                            painterResource(R.drawable.bintang), null,
                            Modifier.padding(top = 5.dp)
                        )
                        Image(
                            painterResource(R.drawable.bintang), null,
                            Modifier.padding(top = 5.dp)
                        )
                        Image(
                            painterResource(R.drawable.bintang), null,
                            Modifier.padding(top = 5.dp)
                        )
                        Image(
                            painterResource(R.drawable.bintang), null,
                            Modifier.padding(top = 5.dp)
                        )
                        Image(
                            painterResource(R.drawable.bintang), null,
                            Modifier.padding(top = 5.dp)
                        )
                    }
                }
                Image(
                    painterResource(R.drawable.figmavektor), null,
                    Modifier.padding(top = 5.dp, end = 5.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Row() {
                    Image(
                        painterResource(R.drawable.rp), null,
                        Modifier.padding(top = 5.dp, end = 5.dp)
                    )
                    Text(
                        "Rp. 150.000",
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF1C6973)),
                    modifier = Modifier
                        .width(150.dp)
                        .padding(end = 50.dp, top = 25.dp)
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