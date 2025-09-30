package com.example.praktikum3_235150200111005ahmadnafimubarok

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktikum3_235150200111005ahmadnafimubarok.ui.theme.Praktikum3_235150200111005AhmadNafiMubarokTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            CounterApp()
//            FollowButton()
//            FollowApp()
//            ProfileCard()
            PlusMinusApp()
//            ToggleApp()
//            ProfileApp()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Praktikum3_235150200111005AhmadNafiMubarokTheme {
        Greeting("Android")
    }
}

@Composable
fun Counter() {
    var count by remember { mutableStateOf(0) }
    Button(onClick = { count++ }) {
        Text("Clicked $count times")
    }
}

//@Composable
//fun FollowButton() {
//    var isFollowed by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Button(onClick = { isFollowed = !isFollowed }) {
//            Text(if (isFollowed) "Unfollow" else "Follow")
//        }
//    }
//}


@Composable
fun FollowButton(
    isFollowed: Boolean,
    onClick: () -> Unit
)

{
    Button(onClick = onClick){
        Text(if (isFollowed) "Unfollow" else "Follow")
    }
}

@Composable
fun FollowApp (){
    var isFollowed by remember { mutableStateOf(false) }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        FollowButton(isFollowed = isFollowed, onClick = { isFollowed = !isFollowed })
    }
}

@Composable
fun ToggleFollowButton(){
    var isOn by remember { mutableStateOf(false) }
    Button(onClick = { isOn = !isOn }){
        Text(if (isOn) "Unfollow" else "Follow")
    }
}

@Composable
fun CounterApp (){
    var count by remember { mutableStateOf(0) }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Jumlah klik : $count")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { count++ }) {
            Text("Tambah")
        }
    }
}

@Composable
fun ProfileCard() {
    var isFollowed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.myphoto),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Ahmad Nafi Mubarok",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("Mahasiswa Teknik Informatika", fontSize = 14.sp)
                Text("Universitas Brawijaya", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { isFollowed = !isFollowed },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFollowed) Color.LightGray else Color(0xFF3897F0),
                        contentColor = if (isFollowed) Color.Black else Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (isFollowed) "Unfollow" else "Follow")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Saya adalah mahasiswa Teknik Informatika Universitas Brawijaya. " +
                                "Minat saya di bidang Data Mining, IoT, dan Mobile Development.",
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}

@Composable
fun PlusMinusApp() {
    var count by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Counter: $count", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Button(
                onClick = { if (count > 0) count-- },
                modifier = Modifier.width(100.dp)
            ) {
                Text("-")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { count++ },
                modifier = Modifier.width(100.dp)
            ) {
                Text("+")
            }
        }
    }
}

@Composable
fun ToggleApp() {
    var isRed by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isRed) Color.Red else Color.Green)
            .clickable { isRed = !isRed },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isRed) "Merah" else "Hijau",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ProfileApp() {
    var isFollowed by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.myphoto),
                    contentDescription = "Foto Profil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Ahmad Nafi Mubarok",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("Mahasiswa Teknik Informatika", fontSize = 14.sp)
                Text("Universitas Brawijaya", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { isFollowed = !isFollowed },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFollowed) Color.LightGray else Color(0xFF3897F0),
                        contentColor = if (isFollowed) Color.Black else Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(if (isFollowed) "Unfollow" else "Follow")
                }

                Spacer(modifier = Modifier.height(12.dp))


                Text(
                    text = if (isFollowed) "Anda mengikuti akun ini"
                    else "Anda belum mengikuti akun ini",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = if (isFollowed) Color(0xFF3897F0) else Color.Gray
                )
            }
        }
    }
}


