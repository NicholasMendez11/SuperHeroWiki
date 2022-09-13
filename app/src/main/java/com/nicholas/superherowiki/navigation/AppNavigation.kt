import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.superheros.navigation.AppScreens


@Composable
fun AppNavigation(checkedState: MutableState<Boolean>) {
    val navController = rememberNavController()
    //El navhost es el que controla toda la navegacion entre pantallas. aqui lo configuramos
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            //Add the SplashScreen once is ready
        }
        composable(AppScreens.MainScreen.route) {
            MainScreen(checkedState, navController)
        }
        composable(
            AppScreens.SuperInfo.route + "/{name}",
            arguments = listOf(navArgument(name = "name") { type = NavType.IntType }) // Use INT because as I'm using a resource id, I will use to receive it and look for the match object.
        ) {
            SuperInfo(navController, it.arguments?.getInt("name"))
        }
    }
}