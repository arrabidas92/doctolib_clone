package com.oway.your.way.`is`.my.way.doctolib_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oway.your.way.`is`.my.way.doctolib_compose.ui.theme.Doctolib_composeTheme

//TODO:Start implementing UI for home screen
@Composable
fun HomeScreen() {
    LazyColumn(
       modifier = Modifier.background(MaterialTheme.colors.primary)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(MaterialTheme.colors.error)
            ) {
                LanguageItem()
            }
        }
    }
}

@Composable
fun LanguageItem() {
    Row (
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
            //TODO:Add clickable action
        }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_public_48px),
            contentDescription = "language icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            "FR",
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_expand_more_48px),
            contentDescription = "expand more icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(16.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LanguageItemPreview() {
    Doctolib_composeTheme {
        LanguageItem()
    }
}