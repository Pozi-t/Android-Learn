package com.example.androidteach

import android.os.Bundle
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidteach.ui.theme.AndroidTeachTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidTeachTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Greeting(
                            name = "Android",
                            onOpenSecondActivity = { text ->
                                val intent = Intent(
                                    this@MainActivity,
                                    SecondActivity::class.java
                                )

                                intent.putExtra("user_text", text)

                                startActivity(intent)
                            },
                            onCallFriend = { phoneNumber ->
                                val digits = phoneNumber.filter { it.isDigit() }
                                val isValidFormat =
                                    phoneNumber.startsWith("+") &&
                                            phoneNumber.drop(1).all { it.isDigit() } &&
                                            digits.length in 10..15
                                if (!isValidFormat) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Введите номер в формате +XXXXXXXXXXX",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }else {
                                    val intent = Intent(
                                        Intent.ACTION_DIAL,
                                        Uri.parse("tel:$phoneNumber")
                                    )

                                    startActivity(intent)
                                }
                            },
                            onShareText = { text ->

                                if (text.isBlank()) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Введите текст для отправки",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }else {
                                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, text)
                                    }
                                    val shareIntent = Intent.createChooser(
                                        sendIntent,
                                        "Поделиться текстом"
                                    )

                                    startActivity(shareIntent)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    onOpenSecondActivity: (String) -> Unit,
    onCallFriend: (String) -> Unit,
    onShareText: (String) -> Unit,
    ) {
    var text by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {text = it}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onOpenSecondActivity(text)
            }
        ){
            Text("Открыть вторую Activity")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onCallFriend(text)
            }
        ){
            Text("Позвонить другу")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onShareText(text)
            }
        ){
            Text("Поделиться текстом")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTeachTheme {
        Greeting(
            name = "Android",
            onOpenSecondActivity = {},
            onCallFriend = {},
            onShareText = {},
        )
    }
}