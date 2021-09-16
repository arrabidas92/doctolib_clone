package com.oway.your.way.`is`.my.way.doctolib_compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oway.your.way.`is`.my.way.doctolib_compose.R.drawable.ic_logo_doctolib
import com.oway.your.way.`is`.my.way.doctolib_compose.ui.theme.Doctolib_composeTheme
import kotlinx.coroutines.delay
import kotlin.math.log

class DoctolibLauncherScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Doctolib_composeTheme {
                LaunchScreen()
            }
        }
    }
}

@Composable
fun LaunchScreen() {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = DoctolibScreen.Splash.route
    ) {
        composable(DoctolibScreen.Splash.route) {
            LaunchScreenUI(navController = navController)
        }
        composable(DoctolibScreen.Home.route) {
            HomeScreen(
                onSearchBarClick = {
                    Log.d("Navigation","onSearchBarClick")
                    //TODO: Navigate to search screen
                }
            )
        }
    }
}

@Composable
fun LaunchScreenUI(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate(DoctolibScreen.Home.route)
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Image(
            painterResource(id = ic_logo_doctolib),
            contentDescription = "Logo Doctolib"
        )
        CircularProgressIndicator(
            color = MaterialTheme.colors.background
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Doctolib_composeTheme {
        LaunchScreen()
    }
}