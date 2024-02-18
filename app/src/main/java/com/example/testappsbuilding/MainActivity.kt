package com.example.testappsbuilding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.testappsbuilding.navigation.AppsBuldingNavHost
import com.example.testappsbuilding.ui.theme.TestAppsBuildingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppsBuildingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestApp()
                }
            }
        }
    }
}

@Composable
fun TestApp(navController: NavHostController = rememberNavController()) {
    AppsBuldingNavHost(navController = navController)
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppsBuildingTheme {
        TestApp()
    }
}