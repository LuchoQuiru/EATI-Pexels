package com.eati.pexels.presentation

import DetailOutlinedCard
import SearchBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eati.pexels.domain.Photo
import com.eati.pexels.presentation.compents.LazyRowOfPictures


@Composable
fun PhotosScreen(viewModel: PhotosViewModel) {
    val result by viewModel.photosFlow.collectAsState()
    Photos(result, viewModel::updateResults)
}

@Composable
fun Photos(results: List<Photo>, updateResults: (String) -> Unit) {

    var selectedPhoto by remember {
        mutableStateOf<Photo?>(null)
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InitialSearch(updateResults)
        SearchBar(updateResults)
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .background(Color.Red)
        )
        LazyRowOfPictures(results) { selectedPhoto = it }
        Spacer(modifier = Modifier.height(8.dp))
        selectedPhoto?.let {
            DetailOutlinedCard(it)
        }
    }
}

@Composable
fun InitialSearch(updateResults: (String) -> Unit) {
    LaunchedEffect(Unit) {
        updateResults("Messi")
    }
}




