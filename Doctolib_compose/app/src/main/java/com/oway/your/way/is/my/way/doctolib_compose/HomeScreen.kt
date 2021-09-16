package com.oway.your.way.`is`.my.way.doctolib_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oway.your.way.`is`.my.way.doctolib_compose.ui.theme.Doctolib_composeTheme

//TODO:Continue implementing UI for home screen

@Composable
fun HomeScreen(onSearchBarClick: () -> Unit) {
    LazyColumn(
       modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
    ) {
        item {
            TopBar(modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(4.dp)
            )
        }
        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
            )
        }
        item {
            SearchCard(
                onSearchBarClick = onSearchBarClick,
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
            )
        }
        item {
            HealthProCard()
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        LanguageItem()
        Image(
            painter = painterResource(id = R.drawable.ic_logo_doctolib),
            modifier = Modifier.fillMaxHeight(),
            contentDescription = "logo doctolib"
        )
        Text(
            stringResource(id = R.string.login),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.background
        )
    }
}

@Composable
fun LanguageItem() {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
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
            style = MaterialTheme.typography.body2,
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

@Composable
fun SearchCard(onSearchBarClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Text(
                stringResource(id = R.string.plan_consultation),
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(16.dp)
            )
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .clickable {
                    onSearchBarClick()
                }
            )
        }
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .height(44.dp)
                .background(
                    Color.LightGray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp)
                )
        ) {
            Text(
                stringResource(id = R.string.search_hint),
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Color(red = 255, green = 160, blue = 0),
                    shape = RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp)
                )
                .size(44.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_search_48px),
                colorFilter = ColorFilter.tint(color = Color.White),
                contentDescription = "logo recherche",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun HealthProCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(MaterialTheme.colors.onPrimary)
            .fillMaxWidth()
            .height(54.dp)
    ) {
        Text(
            stringResource(id = R.string.health_pro),
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_48px),
            colorFilter = ColorFilter.tint(color = Color.White.copy(alpha = 0.75f)),
            contentDescription = "logo chevron droite",
            modifier = Modifier
                .size(26.dp)
        )
    }
}