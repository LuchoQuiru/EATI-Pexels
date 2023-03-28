import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.eati.pexels.domain.Photo


@Composable
fun DetailOutlinedCard(photo: Photo) {
    val roundedValue = 32.dp
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp)
            /*.border(
                width = 1.dp,
                shape = RoundedCornerShape(roundedValue),
                color = MaterialTheme.colors.onBackground
            )*/
            .clip(RoundedCornerShape(roundedValue)),
        backgroundColor = MaterialTheme.colors.onPrimary,
    ) {
        ConstraintLayout(
            modifier = Modifier.clip(RoundedCornerShape(roundedValue))
        ) {
            val (ref_image, ref_photographer, ref_alt) = createRefs()
            Imagen(photo, ref_image)
            Titulo(photo, ref_image, ref_photographer)
            Descripcion(photo, ref_photographer, ref_alt)
        }
    }
}

@Composable
private fun ConstraintLayoutScope.Imagen(
    photo: Photo,
    ref_image: ConstrainedLayoutReference
) {
    SubcomposeAsyncImage(
        model = photo.url,
        contentDescription = null,
        modifier = Modifier
            .constrainAs(ref_image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
    )
    {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            //CircularProgressIndicator(modifier = Modifier.padding(100.dp))
        } else {
            SubcomposeAsyncImageContent(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(32.dp),
                        color = MaterialTheme.colors.onBackground
                    )
                    .heightIn(20.dp,300.dp)
            )
        }
    }
}

@Composable
private fun ConstraintLayoutScope.Titulo(
    photo: Photo,
    ref_image: ConstrainedLayoutReference,
    ref_photographer: ConstrainedLayoutReference,
) {
    Text(
        text = photo.photographer,
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(16.dp)
            .constrainAs(ref_photographer) {
                top.linkTo(ref_image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .widthIn(20.dp,photo.width.dp)
    )

}

@Composable
fun ConstraintLayoutScope.Descripcion(
    photo: Photo,
    ref_photographer: ConstrainedLayoutReference,
    ref_alt: ConstrainedLayoutReference,
) {
    Text(
        text = photo.alt,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .padding(8.dp)
            .constrainAs(ref_alt) {
                top.linkTo(ref_photographer.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .widthIn(20.dp,photo.width.dp)
    )

}


