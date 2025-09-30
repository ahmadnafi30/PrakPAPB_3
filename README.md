# Praktikum 3 – State di Jetpack Compose  

##  Deskripsi  
Pada praktikum ini saya membuat beberapa aplikasi sederhana menggunakan **Jetpack Compose** untuk memahami konsep **state**.  

Aplikasi yang dibuat:  
1. **Counter Plus-Minus** : counter dengan tombol tambah dan kurang, nilainya tidak boleh turun di bawah nol.  
2. **Toggle aaPP** : sebuah box yang bisa berubah warna merah ↔ hijau secara bergantian setiap kali diklik.  
3. **Profil Interaktif** : profil sederhana (foto, nama, deskripsi) dengan tombol Follow/Unfollow dan indikator teks status.  

Saya juga menambahkan variasi seperti `FollowApp`, `FollowButton`, `ProfileCard`, dan `ProfileApp` agar lebih lengkap.  

---

## Implementasi State  
Semua aplikasi menggunakan `remember { mutableStateOf() }` untuk menyimpan nilai state.  
- **Counter / PlusMinusApp** : nilai `count` berubah setiap kali tombol ditekan. Compose otomatis recompose teks di layar. 
- **ToggleApp** : boolean `isRed` mengatur apakah kotak berwarna merah atau hijau. Klik → nilai berganti → warna juga ikut berubah.  
- **ProfileApp / ProfileCard** : tombol Follow/Unfollow memanfaatkan state `isFollowed`. Kalau true maka teks berubah jadi "Unfollow" dan indikator menampilkan "Anda mengikuti akun ini".  

---

## Analisis
Counter Plus-Minus : Saat tombol ditekan, nilai count berubah. Karena count disimpan di remember { mutableStateOf() }, Compose tahu ada perubahan dan otomatis menggambar ulang teks.
Toggle Warna : State boolean isRed jadi penentu warna. Klik kotak → nilai berubah → background ikut berganti. Kalau hanya pakai .background tanpa state, UI tidak bisa berubah dinamis.
Profil Interaktif : Tombol Follow/Unfollow menggunakan state isFollowed. Nilainya berubah setiap kali diklik, lalu Compose otomatis update teks tombol dan indikator. Kalau aplikasi ditutup dan dibuka lagi, state tidak tersimpan karena remember hanya berlaku selama composable aktif. Untuk menyimpan state permanen harus pakai rememberSaveable atau storage lain.
Kesimpulan: Jetpack Compose lebih sederhana dibanding XML tradisional. UI jadi reaktif mengikuti state, kode lebih ringkas, dan gampang dipelihara.

##  Cuplikan Kode  

### Counter Plus-Minus
```kotlin
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
                onClick = { if (count > 0) count-- }, // tidak boleh kurang dari 0
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

```
<img width="2476" height="1339" alt="Screenshot 2025-09-30 231821" src="https://github.com/user-attachments/assets/65be8cf6-48c2-4c20-9c67-bf6be8e625c8" />
<img width="2580" height="1321" alt="Screenshot 2025-09-30 231142" src="https://github.com/user-attachments/assets/c96363f8-b794-45ee-8adf-399acfa4f864" />

### Toggle
```kotlin
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
```

<img width="2549" height="1056" alt="Screenshot 2025-09-30 231740" src="https://github.com/user-attachments/assets/ecefbe3f-aabe-4147-ac81-c146e441b482" />
<img width="2696" height="1325" alt="Screenshot 2025-09-30 231315" src="https://github.com/user-attachments/assets/428b036c-4392-4d03-b568-f8e265ebeac2" />

### Profile
```kotlin
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
```


<img width="2575" height="1479" alt="Screenshot 2025-09-30 231418" src="https://github.com/user-attachments/assets/9698dba0-54fd-4b61-adca-d5324c37cc43" />
<img width="2644" height="1444" alt="Screenshot 2025-09-30 231657" src="https://github.com/user-attachments/assets/87bf96fe-42f8-45c7-90d2-bbd199b9d288" />

---

##  Kesimpulan  
Jetpack Compose lebih sederhana dibanding XML tradisional.  
UI jadi reaktif mengikuti state, kode lebih ringkas, dan gampang dipelihara.
