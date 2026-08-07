package com.anantagita.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anantagita.ui.screens.AboutScreen
import com.anantagita.ui.screens.AdhyayaEightScreen
import com.anantagita.ui.screens.AdhyayaEighteenScreen
import com.anantagita.ui.screens.AdhyayaElevenScreen
import com.anantagita.ui.screens.AdhyayaFifteenScreen
import com.anantagita.ui.screens.AdhyayaFiveScreen
import com.anantagita.ui.screens.AdhyayaFourScreen
import com.anantagita.ui.screens.AdhyayaFourteenScreen
import com.anantagita.ui.screens.AdhyayaNineScreen
import com.anantagita.ui.screens.AdhyayaOneScreen
import com.anantagita.ui.screens.AdhyayaSevenScreen
import com.anantagita.ui.screens.AdhyayaSeventeenScreen
import com.anantagita.ui.screens.AdhyayaSixScreen
import com.anantagita.ui.screens.AdhyayaSixteenScreen
import com.anantagita.ui.screens.AdhyayaTenScreen
import com.anantagita.ui.screens.AdhyayaThirteenScreen
import com.anantagita.ui.screens.AdhyayaThreeScreen
import com.anantagita.ui.screens.AdhyayaTwelveScreen
import com.anantagita.ui.screens.AdhyayaTwoScreen
import com.anantagita.ui.screens.FeedbackScreen
import com.anantagita.ui.screens.HomeScreen
import com.anantagita.ui.screens.PrivacyTermsScreen
import com.anantagita.ui.screens.SplashScreen

sealed class Screen(val route: String) {

    // ---------- Auth ----------
    object Splash : Screen("splash")

    // ---------- Home ----------
    object Home : Screen("home")

    // ---------- Profile / Info ----------
    object Profile : Screen("profile")
    object About : Screen("about")
    object Support : Screen("support")
    object PrivacyTerms : Screen("privacy_terms")
    object Feedback : Screen("feedback")

    // ---------- Adhyaya (Chapters) ----------
    object AdhyayaOne : Screen("adhyaya_one")
    object AdhyayaTwo : Screen("adhyaya_two")
    object AdhyayaThree : Screen("adhyaya_three")
    object AdhyayaFour : Screen("adhyaya_four")
    object AdhyayaFive : Screen("adhyaya_five")
    object AdhyayaSix : Screen("adhyaya_six")
    object AdhyayaSeven : Screen("adhyaya_seven")
    object AdhyayaEight : Screen("adhyaya_eight")
    object AdhyayaNine : Screen("adhyaya_nine")
    object AdhyayaTen : Screen("adhyaya_ten")
    object AdhyayaEleven : Screen("adhyaya_eleven")
    object AdhyayaTwelve : Screen("adhyaya_twelve")
    object AdhyayaThirteen : Screen("adhyaya_thirteen")
    object AdhyayaFourteen : Screen("adhyaya_fourteen")
    object AdhyayaFifteen : Screen("adhyaya_fifteen")
    object AdhyayaSixteen : Screen("adhyaya_sixteen")
    object AdhyayaSeventeen : Screen("adhyaya_seventeen")
    object AdhyayaEighteen : Screen("adhyaya_eighteen")
}

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        // ---------- Splash ----------
        composable(Screen.Splash.route) {
            SplashScreen(
                navController = navController,
                onFinished = {
                    navController.navigate(Screen.Home.route) {
                        // Clears the splash screen from the backstack
                        popUpTo(Screen.Splash.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // ---------- Home ----------
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        // ---------- Info Screens ----------
        composable(Screen.About.route) { AboutScreen(navController) }
        composable(Screen.Feedback.route) { FeedbackScreen(navController) }
        composable(Screen.PrivacyTerms.route) { PrivacyTermsScreen(navController) }

        // ---------- Adhyaya ----------
        addAdhyayaNavGraph(navController)
    }
}

fun NavGraphBuilder.addAdhyayaNavGraph(navController: NavHostController) {
    composable(Screen.AdhyayaOne.route) { AdhyayaOneScreen(navController) }
    composable(Screen.AdhyayaTwo.route) { AdhyayaTwoScreen(navController) }
    composable(Screen.AdhyayaThree.route) { AdhyayaThreeScreen(navController) }
    composable(Screen.AdhyayaFour.route) { AdhyayaFourScreen(navController) }
    composable(Screen.AdhyayaFive.route) { AdhyayaFiveScreen(navController) }
    composable(Screen.AdhyayaSix.route) { AdhyayaSixScreen(navController) }
    composable(Screen.AdhyayaSeven.route) { AdhyayaSevenScreen(navController) }
    composable(Screen.AdhyayaEight.route) { AdhyayaEightScreen(navController) }
    composable(Screen.AdhyayaNine.route) { AdhyayaNineScreen(navController) }
    composable(Screen.AdhyayaTen.route) { AdhyayaTenScreen(navController) }
    composable(Screen.AdhyayaEleven.route) { AdhyayaElevenScreen(navController) }
    composable(Screen.AdhyayaTwelve.route) { AdhyayaTwelveScreen(navController) }
    composable(Screen.AdhyayaThirteen.route) { AdhyayaThirteenScreen(navController) }
    composable(Screen.AdhyayaFourteen.route) { AdhyayaFourteenScreen(navController) }
    composable(Screen.AdhyayaFifteen.route) { AdhyayaFifteenScreen(navController) }
    composable(Screen.AdhyayaSixteen.route) { AdhyayaSixteenScreen(navController) }
    composable(Screen.AdhyayaSeventeen.route) { AdhyayaSeventeenScreen(navController) }
    composable(Screen.AdhyayaEighteen.route) { AdhyayaEighteenScreen(navController) }
}