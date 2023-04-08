package com.example.collpage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.collpage.R
import com.example.collpage.helper.getInputColor
import com.example.collpage.ui.ActivityNote
import com.example.collpage.ui.MainViewModel
import com.example.collpage.ui.SheetContent
import com.example.collpage.ui.User
import com.example.collpage.ui.theme.Poppins
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToWelcome: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToMessage: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: MainViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })
    val scope = rememberCoroutineScope()
    val userData = viewModel.user

    LaunchedEffect(key1 = true) {
        viewModel.currentUserId = Firebase.auth.currentUser?.uid.toString()
        if (userData == User()) {
            viewModel.getUserData(viewModel.currentUserId)
        }
    }

    ModalBottomSheetLayout(
        {
            if (viewModel.sheetContent == SheetContent.NOTE) QuickNoteSheet() else CommentSheet()
        }, sheetState = sheetState, sheetShape = RoundedCornerShape(20.dp, 20.dp)
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(
                    {
                        viewModel.sheetContent = SheetContent.NOTE
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
            topBar = {
                HomeTopAppBar {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            },
            bottomBar = { BottomBar(viewModel, navigateToMessage, navigateToHome) },
            drawerContent = {
                HomeNavDrawer(userData) {
                    Firebase.auth.signOut()
                    viewModel.clearUserData()
                    navigateToWelcome()
                }
            }) {
            Column(Modifier.padding(it)) {
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 25.dp, horizontal = 20.dp),
                    Arrangement.Center
                ) {
                    Row(
                        Modifier
                            .width(290.dp)
                            .height(60.dp)
                            .padding(end = 12.dp)
                            .background(
                                getInputColor(), RoundedCornerShape(35.dp)
                            )
                            .clickable { navigateToSearch() }) {
                        Icon(
                            painterResource(R.drawable.search),
                            null,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .padding(top = 12.dp, start = 25.dp)
                                .size(35.dp)
                        )
                        Text(
                            "Search...",
                            fontFamily = Poppins,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 20.dp, top = 16.dp),
                            color = Color(0xFF909090)
                        )
                    }
                    Image(painterResource(R.drawable.default_profile),
                        null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .clickable { navigateToProfile() })
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp), Arrangement.SpaceBetween
                ) {
                    Text(
                        "Berita", fontFamily = Poppins,
                        fontSize = 20.sp, fontWeight = FontWeight.SemiBold,
                    )
                    IconButton(onClick = { /*TODO*/ }, Modifier.size(20.dp)) {
                        Icon(painterResource(R.drawable.collapse), null)
                    }
                }
                Surface(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 35.dp),
                    RoundedCornerShape(25.dp),
                    Color(0xFFE5E8CD)
                ) {
                    Row(Modifier.padding(start = 24.dp), Arrangement.SpaceBetween) {
                        Column(Modifier.padding(top = 25.dp)) {
                            Text(
                                "Raih Pekerjaan Impianmu",
                                Modifier.width(195.dp),
                                Color.Black,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 23.sp,
                            )
                            Surface(
                                Modifier
                                    .width(105.dp)
                                    .padding(top = 5.dp, bottom = 10.dp)
                                    .clickable { }, RoundedCornerShape(15.dp), Color(0xFF1C6973)
                            ) {
                                Text(
                                    "Cari Kerja",
                                    fontFamily = Poppins,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        Image(
                            painterResource(R.drawable.image),
                            null,
                            Modifier.padding(top = 8.dp, end = 25.dp)
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
                Card(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Column(Modifier.padding(top = 8.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            Arrangement.SpaceBetween
                        ) {
                            Row {
                                Image(
                                    painterResource(R.drawable.microsoft),
                                    null,
                                    Modifier.padding(top = 5.dp)
                                )
                                Text(
                                    "Microsoft",
                                    Modifier.padding(start = 5.dp),
                                    fontFamily = Poppins
                                )
                            }
                            Icon(painterResource(R.drawable.bookmark), null)
                        }
                        Text(
                            "We Are Hiring!",
                            Modifier.padding(horizontal = 20.dp),
                            fontFamily = Poppins,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            "For more information : ",
                            Modifier
                                .padding(horizontal = 20.dp)
                                .padding(bottom = 10.dp),
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Image(
                            painterResource(R.drawable.rectangle_2), null
                        )
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp),
                            Arrangement.SpaceBetween
                        ) {
                            IconButton({ /*TODO*/ }) {
                                Icon(painterResource(R.drawable.heart), null)
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painterResource(R.drawable.chat), null)
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(painterResource(R.drawable.share), null)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeTopAppBar(onNavIconClick: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.padding(start = 10.dp, top = 15.dp)) {
            IconButton(onClick = onNavIconClick) {
                Icon(painterResource(R.drawable.nav_drawer), null)
            }
        }
        Row(Modifier.padding(end = 12.dp, top = 20.dp)) {
            Row(
                Modifier
                    .padding(end = 8.dp)
                    .background(
                        getInputColor(), RoundedCornerShape(30.dp)
                    )
                    .clickable { }
            ) {
                Icon(
                    painterResource(R.drawable.location),
                    null,
                    tint = Color(0xFF1C6973),
                    modifier = Modifier.size(40.dp).padding(vertical = 8.dp)
                )
                Text(
                    "Malang",
                    fontFamily = Poppins,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 5.dp, end = 10.dp),
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.onSurface
                )
                Icon(
                    painterResource(R.drawable.arrow_down),
                    null,
                    Modifier.size(32.dp).padding(top = 11.dp, end = 12.dp)
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    viewModel: MainViewModel,
    navigateToMessage: () -> Unit,
    navigateToHome: () -> Unit
) {
    val homeIcon = if (viewModel.selectedItem == "Home") R.drawable.home_active else R.drawable.home
    val homeIconTint = if (viewModel.selectedItem == "Home") Color(0xFF1C6973)
    else MaterialTheme.colors.onSurface

    val messageIcon = if (viewModel.selectedItem == "Mail") R.drawable.mail_active else R.drawable.mail
    val messageIconTint = if (viewModel.selectedItem == "Mail") Color(0xFF1C6973)
    else MaterialTheme.colors.onSurface

    BottomAppBar(backgroundColor = MaterialTheme.colors.surface, cutoutShape = CircleShape) {
        BottomNavigationItem(
            viewModel.selectedItem == "Home",
            {
                viewModel.selectedItem = "Home"
                navigateToHome()
            },
            { Icon(painterResource(homeIcon), null, tint = homeIconTint) }
        )
        BottomNavigationItem(selected = false, onClick = {}, icon = {}, enabled = false)
        BottomNavigationItem(
            selected = viewModel.selectedItem == "Mail",
            onClick = {
                viewModel.selectedItem = "Mail"
                navigateToMessage()
            },
            icon = { Icon(painterResource(messageIcon), null, tint = messageIconTint) },
        )
    }
}

@Composable
fun HomeNavDrawer(userData: User, onLogoutClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1C6973))
            .padding(start = 23.dp, top = 50.dp)
    ) {
        Image(
            painterResource(R.drawable.default_profile),
            null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
        )
        Text(
            "Selamat Datang!",
            fontFamily = Poppins,
            fontSize = 23.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFE5E8CD)
        )
        Text(
            userData.name,
            fontFamily = Poppins,
            fontSize = 23.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Button(
            onClick = { },
            Modifier
                .height(60.dp)
                .padding(top = 8.dp),
            shape = RoundedCornerShape(17.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF4E8989))
        ) {
            Icon(painterResource(R.drawable.settings), null, tint = Color.White)
            Text(
                "Pengaturan",
                Modifier.padding(start = 10.dp),
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
        Button(
            onClick = onLogoutClick,
            shape = RoundedCornerShape(17.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Log Out", fontFamily = Poppins, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun QuickNoteSheet() {
    var activeCard by remember { mutableStateOf("Jadwal") }
    val activityNoteList: List<ActivityNote> = listOf(
        ActivityNote(
            "Jadwal",
            "24 Acara",
            R.drawable.calendar,
            Color(0xFF1C6973),
            Color(0xFFA8E2EA),
            Color(0xFFD4F1F5),
            Color(0xFFB2EEF3),
            R.drawable.calendar_back
        ), ActivityNote(
            "Catatan",
            "12 Konten",
            R.drawable.note,
            Color(0xFFE93030),
            Color(0xFFF7CDCD),
            Color(0xFFFBE6E6),
            Color(0xFFFED6D6),
            R.drawable.note_back
        ), ActivityNote(
            "Tugas",
            "6 Proyek",
            R.drawable.project,
            Color(0xFF97A04A),
            Color(0xFFD7DBB2),
            Color(0xFFE1E4C5),
            Color(0xFFEFF2C8),
            R.drawable.task_back
        )
    )

    Column(Modifier.fillMaxHeight()) {
        Row(Modifier.fillMaxWidth().padding(top = 17.dp), Arrangement.Center) {
            Divider(
                Modifier
                    .background(color = Color.LightGray, shape = RoundedCornerShape(2.dp))
                    .width(250.dp), thickness = 5.dp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(horizontal = 18.dp),
            Arrangement.SpaceBetween
        ) {
            Text(
                "Aktivitas",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painterResource(R.drawable.notification_bell), null)
            }
        }
        Surface(
            Modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            RoundedCornerShape(22.dp),
            Color(0xFFF2B46B)
        ) { }
        Spacer(Modifier.height(10.dp))
        LazyRow(Modifier.padding(end = 18.dp)) {
            items(activityNoteList) {
                val activeBgColor =
                    if (activeCard == it.title) it.activeBgColor else getInputColor()
                val activeIconTint = if (activeCard == it.title) it.activeIconTint
                else Color(0xFF696969)
                val activeIconBgColor = if (activeCard == it.title) it.activeIconBgColor
                else Color(0xFFF5F5F5)
                val activeBackIconColor = if (activeCard == it.title) it.activeBackIconColor
                else Color(0xFFF3F3F3)
                ActivityNoteCard(
                    it, activeBgColor, activeIconTint, activeIconBgColor, activeBackIconColor
                ) { activeCard = it.title }
            }
        }
    }
}

@Composable
fun CommentSheet() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 17.dp), Arrangement.Center
    ) {
        Divider(
            Modifier
                .background(color = Color.LightGray, shape = RoundedCornerShape(2.dp))
                .width(250.dp),
            thickness = 5.dp
        )
    }
}

@Composable
fun ActivityNoteCard(
    note: ActivityNote,
    activeBgColor: Color,
    activeIconTint: Color,
    activeIconBgColor: Color,
    activeBackIconColor: Color,
    onCardClick: () -> Unit
) {
    Card(
        Modifier
            .width(170.dp)
            .padding(start = 18.dp)
            .clickable { onCardClick() },
        RoundedCornerShape(15.dp),
        activeBgColor
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 18.dp), Arrangement.End
        ) {
            Icon(painterResource(note.backIconId), null, tint = activeBackIconColor)
        }
        Column(
            Modifier
                .padding(top = 18.dp)
                .padding(horizontal = 12.dp)
        ) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Surface(
                    Modifier.size(50.dp), CircleShape, activeIconBgColor
                ) {
                    Icon(
                        painterResource(note.iconId), null, Modifier.padding(14.dp), activeIconTint
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
            Text(
                note.title, fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 22.sp
            )
            Text(note.desc, fontFamily = Poppins, fontSize = 17.sp)
            Spacer(Modifier.height(20.dp))
        }
    }
}
