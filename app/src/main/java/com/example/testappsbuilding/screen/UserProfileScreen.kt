package com.example.testappsbuilding.screen

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.example.testappsbuilding.AppViewModelProvider
import com.example.testappsbuilding.R
import com.example.testappsbuilding.model.MarsPhoto
import com.example.testappsbuilding.navigation.NavigationDestination


object ProfileDestination : NavigationDestination {
    override val route = "profileScreen"
    override val titleRes = R.string.screen_profile
}

@Composable
fun ProfileScreen(
    retryAction: () -> Unit,
    navigateBack:()->Unit,
    modifier: Modifier = Modifier,
    viewModel: UserProfileScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
){
    val unsplashUiState: MarsUiState = viewModel.marsUiState


    when (val currentState: MarsUiState = unsplashUiState) {

        is MarsUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is MarsUiState.Success ->
            SuccessScreen(
                viewModel = viewModel,
                modifier = modifier,
                photoSource = currentState.photo,
            )

        else -> {
            ErrorScreen(retryAction, modifier,viewModel)
        }

    }
}

@Composable
fun SuccessScreen(
    viewModel: UserProfileScreenViewModel,
    modifier: Modifier,
    photoSource: MarsPhoto?,
) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
                    Text(
                        text = "Name:${viewModel.getName().collectAsState().value}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Age:${viewModel.getAge().collectAsState().value}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                                .clip(CircleShape)
//                            .background(Color.Gray)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(photoSource?.imgSrc)
                                .crossfade(true)
                                .build(),
                            error = painterResource(R.drawable.ic_broken_image),
                            placeholder = painterResource(R.drawable.load),
                            contentDescription = stringResource(R.string.random_photo),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(180.dp)
                        )
                    }
                //}
            }
        }
    }
}


@Composable
fun ErrorScreen(retryAction: () -> Unit,
                modifier: Modifier = Modifier,
                viewModel: UserProfileScreenViewModel,) {

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Name:${viewModel.getName().collectAsState().value}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Age:${viewModel.getAge().collectAsState().value}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.connect_to_internet),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }
}

@Composable
fun LoadingScreen( modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.load),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

