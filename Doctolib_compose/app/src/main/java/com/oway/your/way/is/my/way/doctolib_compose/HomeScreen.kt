package com.oway.your.way.`is`.my.way.doctolib_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
            )
        }
        item {
            InfoCard(
                modifier = Modifier.padding(16.dp)
            )
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

@Composable
fun InfoCard(modifier: Modifier = Modifier) {
    Card (
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color.Green.copy(alpha = 0.25f),
                        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_info_24px),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.secondaryVariant),
                    contentDescription = "icon information",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    stringResource(id = R.string.informations),
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                stringResource(id = R.string.vaccination_new_hours),
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                stringResource(id = R.string.vaccination_body),
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.size(16.dp))
            AnnotatedClickableText(
                unclickableText = stringResource(id = R.string.vaccination_knowledge),
                clickableText = stringResource(id = R.string.mode_emploi),
                url = stringResource(id = R.string.mode_emploi_url),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnnotatedClickableText(
                unclickableText = stringResource(id = R.string.stat),
                clickableText = stringResource(id = R.string.statistics),
                url = stringResource(id = R.string.stat_url),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            ButtonCTA(
                action = stringResource(id = R.string.take_appointment),
                textColor = Color.White
            )
            Spacer(modifier = Modifier.size(24.dp))
            Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}

@Composable
fun ButtonCTA(
    action: String,
    textColor: Color
) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            action.uppercase(),
            color = textColor,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun AnnotatedClickableText(
    unclickableText: String,
    clickableText: String,
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val annotatedText = buildAnnotatedString {
        append("$unclickableText ")

        // We attach this *URL* annotation to the following content
        // until `pop()` is called
        pushStringAnnotation(tag = "URL", annotation = url)
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(clickableText)
        }

        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            // We check if there is an *URL* annotation attached to the text
            // at the clicked position
            annotatedText.getStringAnnotations(tag = "URL", start = offset,
                end = offset)
                .firstOrNull()?.let { uriHandler.openUri(url) }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun InfoCardPreview() {
    Doctolib_composeTheme {
        InfoCard()
    }
}