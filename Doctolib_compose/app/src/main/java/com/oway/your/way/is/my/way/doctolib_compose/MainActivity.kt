package com.oway.your.way.`is`.my.way.doctolib_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.oway.your.way.`is`.my.way.doctolib_compose.ui.theme.Doctolib_composeTheme

//TODO:Add route navigation to home page after delay
class MainActivity : ComponentActivity() {
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
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        Image(
            painterResource(id = R.drawable.ic_logo_doctolib),
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