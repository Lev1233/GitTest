package com.example.testappsbuilding.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testappsbuilding.AppViewModelProvider
import com.example.testappsbuilding.R
import com.example.testappsbuilding.navigation.NavigationDestination

object AgeDestination : NavigationDestination {
    override val route = "ageScreen"
    override val titleRes = R.string.screen_name
}
@Composable
fun AgeScreen(
    navigateToProfile:()->Unit,
    navigateBack:()->Unit,
    viewModel: UserAgeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    var showError by remember { mutableStateOf(false) }

    Surface(color = Color.White) {
        Column(modifier = Modifier.padding(16.dp)) {
            var age by rememberSaveable { mutableStateOf("") }
            TextField(
                value = age,
                onValueChange = { age = it },
                label = { Text (text = stringResource(id = R.string.age_text))},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (showError) {
                Text(
                    text = stringResource(id = R.string.age_error_text),
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Button(onClick = {
                if (age.isNotBlank()) {
                    try {
                        val ageInt = age.toInt()
                        viewModel.setAge(ageInt)
                        navigateToProfile()
                    } catch (e: NumberFormatException) {
                        showError = true
                    }
                } else {
                    showError = true
                }
            }) {
                Text(text = stringResource(id = R.string.next_text))
            }
        }
    }

}