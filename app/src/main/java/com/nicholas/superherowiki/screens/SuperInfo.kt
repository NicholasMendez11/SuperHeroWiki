import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SuperInfo(navController: NavHostController, text: Int?) {
    val superheroName =
        superHeroes.find { it.name == text } // Single source of truth. Check that baby ?


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        superheroName?.let {
            SetImage(superheroName.Wallpaper, superheroName.name)

        }

        superheroName?.let { Information(superheroName.description) }
        ButtonBack(navController)
        Spacer(modifier = Modifier.weight(1f))


    }


}

@Composable
fun Information(description: Int) {
    Box(modifier = Modifier.fillMaxWidth().padding(20.dp), contentAlignment = Alignment.Center ) {
        Text(text = stringResource(description),)
    }


}


@Composable
fun SetImage(imageResourceId: Int, name: Int) {
    Card(elevation = 10.dp, shape = RoundedCornerShape(0.dp), modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = imageResourceId), contentDescription = "Image")
        Text(
            text = stringResource(id = name),
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
    }

}

@Composable
fun ButtonBack(navController: NavHostController) {
    Button(
        onClick = { navController.popBackStack() },
        modifier = Modifier.background(MaterialTheme.colors.surface)
    ) {
        Text(
            text = "Go back",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h1
        )

    }
}

