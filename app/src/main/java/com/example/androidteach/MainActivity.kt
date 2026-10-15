package com.example.androidteach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androidteach.ui.theme.AndroidTeachTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTeachTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun AppListScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF6200EE))
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 16.dp, end = 12.dp)
                    .size(40.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Иконка приложения",
            )
            Text(
                text = "Android Teach",
                color = Color.White,
                fontSize = 20.sp
            )
        }
        LazyColumn {
            items(applications) { app ->
                AppItem(app = app)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun AppItem(app: AppInfo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = app.icon),
                contentDescription = app.name,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(48.dp),
            )
            Column {
                Text(
                    text = app.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = app.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = app.category,
                    fontSize = 12.sp,
                    color = Color(0xFF6200EE)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTeachTheme {
        AppListScreen()
    }
}