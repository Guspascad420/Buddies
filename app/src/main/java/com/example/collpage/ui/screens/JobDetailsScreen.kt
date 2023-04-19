package com.example.collpage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.Job
import com.example.collpage.ui.theme.Poppins
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@OptIn(ExperimentalPagerApi::class)
@Composable
fun JobDetailsScreen(job: Job) {
    val pagerItems: List<@Composable () -> Unit> = listOf(
        { OverviewSection() }, { InformationSection() }, { AboutSection() }
    )

    Scaffold(bottomBar = { DetailsBottomBar() }) {
        Column(Modifier.padding(it)) {
            Box {
                Image(
                    painterResource(R.drawable.rectangle_147), null,
                    Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(50.dp, 50.dp, 25.dp, 25.dp))
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 40.dp),
                    Arrangement.SpaceBetween
                ) {
                    Surface(Modifier, CircleShape) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painterResource(R.drawable.back_arrow), null)
                        }
                    }
                    Surface(Modifier, CircleShape) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(painterResource(R.drawable.back_arrow), null)
                        }
                    }
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .offset(y = (-17).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Image(painterResource(R.drawable.company_logo), null)
                    if (job.company?.is_verified == true) {
                        Icon(
                            painterResource(R.drawable.verified), null,
                            Modifier.padding(top = 17.dp, start = 22.dp)
                        )
                    }
                }
                Text(job.name, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFD9D9D9)) {
                    Text(
                        job.placement, Modifier.padding(5.dp),
                        fontFamily = Poppins, color = Color(0xFF696969)
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Surface(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                RoundedCornerShape(20.dp),
                getInputColor()
            ) {
                Row(Modifier.padding(5.dp), Arrangement.SpaceBetween) {
                    Text("Ringkasan", fontFamily = Poppins, color = Color(0xFF909090))
                    Text("Informasi", fontFamily = Poppins, color = Color(0xFF909090))
                    Text("Tentang", fontFamily = Poppins, color = Color(0xFF909090))
                }
            }
            HorizontalPager(count = 3) {page ->
                pagerItems[page]()
            }
        }
    }
}

@Composable
fun DetailsBottomBar() {
    Surface(Modifier, RoundedCornerShape(20.dp, 20.dp)) {
        Row(Modifier.fillMaxWidth().padding(7.dp)) {
            Button(
                onClick = { },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF1C6973)
                )
            ) {
                Text(
                    "Ajukan", fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold, fontSize = 22.sp
                )
            }
            Surface(Modifier.padding(start = 8.dp), RoundedCornerShape(12.dp), getInputColor()) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(R.drawable.paper_plane), null,
                        tint = Color(0xFF696969)
                    )
                }
            }
            Surface(Modifier.padding(start = 8.dp), RoundedCornerShape(12.dp), Color(0xFFC1C888)) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painterResource(R.drawable.link_chain), null,
                        tint = Color(0xFF696969)
                    )
                }
            }
        }
    }
}

@Composable
fun OverviewSection() {
    Column(Modifier.fillMaxSize(), Arrangement.Top, Alignment.Start) {
        Text("Perekrutan", fontFamily = Poppins)
    }
}

@Composable
fun InformationSection() {
    Text("Kualifikasi", fontFamily = Poppins)
}

@Composable
fun AboutSection() {
    Text("Tentang Perusahaan", fontFamily = Poppins)
}