package com.example.testappsbuilding.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testappsbuilding.AppViewModelProvider
import com.example.testappsbuilding.R
import com.example.testappsbuilding.navigation.NavigationDestination



object HomeDestination : NavigationDestination {
    override val route = "nameScreen"
    override val titleRes = R.string.screen_name
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameScreen(
    navigateToAge:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: UserNameScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    var showError by remember { mutableStateOf(false) }

    Surface(color = Color.White) {
        Column(modifier = Modifier.padding(16.dp)) {
            var name by rememberSaveable { mutableStateOf("") }
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(id = R.string.name_text)) }
            )
            if (showError) {
                Text(
                    text = "Enter your name",
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(onClick = {
                if (name.isNotEmpty()) {
                    viewModel.setName(name)
                    navigateToAge()
                } else {
                    showError = true
                }
            }) {
                Text("Next")
            }
        }
    }
}