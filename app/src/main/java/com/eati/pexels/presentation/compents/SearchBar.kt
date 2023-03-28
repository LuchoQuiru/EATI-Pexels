import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun PreviewSearchBar() {
    SearchBar(updateResults = {})
}

@Composable
fun SearchBar(updateResults: (String) -> Unit) {
    var search by remember {
        mutableStateOf("Messi")
    }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (searchBar, searchButton) = createRefs()

        TextField(
            value = search,
            modifier = Modifier
                .constrainAs(searchBar) {}
                .padding(8.dp),
            onValueChange = {
                search = it
            },
            shape = RoundedCornerShape(32.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text("Busqueda")
            }
        )

        Icon(
            Icons.Default.Search,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .constrainAs(searchButton) {
                    top.linkTo(searchBar.top)
                    bottom.linkTo(searchBar.bottom)
                    end.linkTo(parent.end)
                }
                .padding(end = 16.dp)
                .clickable { updateResults(search) },
        )
        /*Button(
            modifier = Modifier
                .constrainAs(searchButton) {
                    top.linkTo(searchBar.top)
                    bottom.linkTo(searchBar.bottom)
                    start.linkTo(searchBar.end)
                }
                .padding(start = 8.dp),
            onClick = { updateResults(search) },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
        ) {
            Text("Buscar")
        }*/
    }
}

/*@Composable
fun RoundedIcon() {
    Icon(
        Icons.Default.Search,
        contentDescription = null,
        tint = MaterialTheme.colors.onBackground,
        modifier = Modifier
            *//*.constrainAs(searchButton) {
                top.linkTo(searchBar.top)
                bottom.linkTo(searchBar.bottom)
                start.linkTo(searchBar.end)
            }*//*
            .padding(start = 8.dp)
            .clickable { updateResults(search) },
    )
}*/

