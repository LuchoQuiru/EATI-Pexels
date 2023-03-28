package com.eati.pexels.presentation.compents

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.eati.pexels.R
import com.eati.pexels.domain.Photo


@Composable
fun LazyRowOfPictures(results: List<Photo>, onClick: (Photo) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(results){ photo ->
            RoundedPictureUrl(photo,onClick)
        }
    }
}



@Composable
fun RoundedPictureUrl(photo: Photo, onClick: (Photo) -> Unit) {
    SubcomposeAsyncImage(
        model = photo.url,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(88.dp)
            .clip(CircleShape)
            .clickable {
                onClick(photo) //it = photo
            }
    )
    {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            CircularProgressIndicator(
                //modifier = Modifier.size(24.dp)
            )
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

@Preview
@Composable
fun PreviewLazyRowOfLocalPictures() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(4){ item ->
            //RoundedPictureUrl(photo.url,onClick)
            RoundedPictureLocal()
        }
    }
}

@Composable
fun RoundedPictureLocal() {
    Image(
        painter = painterResource(id = R.drawable.messi),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(88.dp)
            .clip(CircleShape)
            .clickable {
                //onClick(R.drawable.messi)
            }
    )
}