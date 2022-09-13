import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.superheros.navigation.AppScreens

@Composable
fun MainScreen(checkedState: MutableState<Boolean>, navController: NavHostController) {

    Scaffold(
        topBar = { ItemTopAppBar(checkedState) }
    ) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            items(superHeroes) {
                superHeroItem(superHero = it,modifier = Modifier, navController)
            }
        }
    }


}

@Composable
fun ItemTopAppBar(checkedState: MutableState<Boolean>) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "SuperHeroes", style = MaterialTheme.typography.h1)
        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = SwitchDefaults.colors()
        )
    }

}

@Composable
fun superHeroItem(superHero: SuperHero, modifier: Modifier = Modifier, navController: NavHostController,) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { navController.navigate(AppScreens.SuperInfo.route + "/${superHero.name}") }, elevation = 2.dp
    ) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,


                )
            {
                ItemButton(expanded) { expanded = !expanded }
                SuperInformation(superHero.RealName, superHero.name)
                Spacer(modifier = modifier.weight(1f))
                SuperHeroImage(superHero.imageResourceId)
            }
            if (expanded) SuperDescription(superHero.description)


        }

    }

}

@Composable
fun SuperDescription(description: Int) {
    Text(text = stringResource(description), style = MaterialTheme.typography.h3)

}

@Composable
fun ItemButton(
    expanded: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.secondary,
            contentDescription = null
        )

    }

}

@Composable
fun SuperHeroImage(imageResourceId: Int) {
    Image(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(16)),
        painter = painterResource(id = imageResourceId),
        contentDescription = "Super Hero"
    )

}

@Composable
fun SuperInformation(realName: Int, name: Int) {
    Column() {
        Text(text = stringResource(name), style = MaterialTheme.typography.h3)
        Text(text = stringResource(realName), style = MaterialTheme.typography.body1)
    }

}








