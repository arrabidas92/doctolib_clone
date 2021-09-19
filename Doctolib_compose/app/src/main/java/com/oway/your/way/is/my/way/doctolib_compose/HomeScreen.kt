package com.oway.your.way.`is`.my.way.doctolib_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//TODO:Add navigation when clicking se connecter button

@ExperimentalMaterialApi
@Composable
fun HomeScreen(onSearchBarClick: () -> Unit) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var countrySelected by remember { mutableStateOf(Country.FRANCE) }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = MaterialTheme.colors.background,
        sheetShape = MaterialTheme.shapes.small,
        sheetContent = {
            BottomSheetContent(
                bottomSheetState = bottomSheetState,
                coroutineScope = coroutineScope,
                onCheckedCountry = {
                    countrySelected = it
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
        ) {
            item {
                TopBar(
                    countrySelected = countrySelected,
                    onLanguageClick = { coroutineScope.launch { bottomSheetState.show() }},
                    modifier = Modifier
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
            item {
                WhiteSection()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    onCheckedCountry: (Country) -> Unit
) {
    val countries = listOf(
        Country.GERMANY,
        Country.FRANCE,
        Country.ITALIA
    )
    var countrySelected by remember { mutableStateOf(Country.FRANCE) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                stringResource(id = R.string.choose_your_country),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.ic_close_48px),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        coroutineScope.launch {
                            countrySelected = Country.FRANCE
                            bottomSheetState.hide()
                        }
                    }
            )
        }
        Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        for (country in countries) {
            LanguageRow(
                country = country,
                currentCountrySelected = countrySelected,
                onCheckedCountry = {
                    countrySelected = it
                }
            )
            Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }
        Spacer(modifier = Modifier.size(16.dp))
        WarningBox()
        Spacer(modifier = Modifier.size(16.dp))
        ButtonCTA(
            action = stringResource(id = R.string.confirm),
            textColor = MaterialTheme.colors.background,
            onCTA = {
                onCheckedCountry(countrySelected)
                coroutineScope.launch { bottomSheetState.hide() }
            }
        )
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun LanguageRow(
    country: Country,
    currentCountrySelected: Country,
    onCheckedCountry: (Country) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primaryVariant),
                checked = country == currentCountrySelected,
                onCheckedChange = { onCheckedCountry(country) }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                country.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.body2
            )
        }
        Image(
            painter = painterResource(id = country.resource),
            contentDescription = "language icon"
        )
    }
}

@Composable
fun WarningBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .background(
                color = MaterialTheme.colors.secondary.copy(alpha = 0.25f),
                shape = MaterialTheme.shapes.small
            )
            .border(
                1.dp,
                color = MaterialTheme.colors.secondary,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            stringResource(id = R.string.warning_select_language),
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun TopBar(countrySelected: Country, onLanguageClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        LanguageItem(countrySelected, onLanguageClick)
        Image(
            painter = painterResource(id = R.drawable.ic_logo_doctolib),
            modifier = Modifier.fillMaxHeight(),
            contentDescription = "logo doctoral"
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
fun LanguageItem(countrySelected: Country, onLanguageClick: () -> Unit) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onLanguageClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_public_48px),
            contentDescription = "language icon",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            countrySelected.abbreviation,
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
            contentDescription = "logo chevron right",
            modifier = Modifier
                .size(26.dp)
        )
    }
}

@Composable
fun InfoCard(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    val prescriptionKnowledgeURL = stringResource(id = R.string.prescription_knowledge_url)

    Card (
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier
    ) {
        Column {
            InfoHeader()
            InfoSection(
                header = stringResource(id = R.string.vaccination_new_hours),
                body = stringResource(id = R.string.vaccination_body)
            )
            Spacer(modifier = Modifier.size(16.dp))
            AnnotatedClickableText(
                uriHandler = uriHandler,
                unclickableText = stringResource(id = R.string.vaccination_knowledge),
                clickableText = stringResource(id = R.string.mode_emploi),
                url = stringResource(id = R.string.mode_emploi_url),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            AnnotatedClickableText(
                uriHandler = uriHandler,
                unclickableText = stringResource(id = R.string.stat),
                clickableText = stringResource(id = R.string.statistics),
                url = stringResource(id = R.string.stat_url),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            InfoFooter(action = stringResource(id = R.string.take_appointment))
            InfoSection(
                header = stringResource(id = R.string.share_prescription),
                body = stringResource(id = R.string.share_prescription_body)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                stringResource(id = R.string.prescription_knowledge),
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .clickable {
                        uriHandler.openUri(prescriptionKnowledgeURL)
                    }
            )
            InfoFooter(action = stringResource(id = R.string.access_documents_cta))
            InfoSection(
                header = stringResource(id = R.string.privacy_header),
                body = stringResource(id = R.string.privacy_body)
            )
            ButtonCTA(
                action = stringResource(id = R.string.discover_engagements),
                textColor = Color.White,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                onCTA = { }
            )
        }
    }
}

@Composable
fun InfoHeader() {
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
}

@Composable
fun InfoSection(header: String, body: String) {
    Text(
        header,
        color = MaterialTheme.colors.secondaryVariant,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )
    Text(
        body,
        color = MaterialTheme.colors.secondaryVariant,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun InfoFooter(action: String) {
    Spacer(modifier = Modifier.size(16.dp))
    ButtonCTA(
        action = action,
        textColor = Color.White,
        onCTA = { }
    )
    Spacer(modifier = Modifier.size(24.dp))
    Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
    Spacer(modifier = Modifier.size(8.dp))
}

@Composable
fun ButtonCTA(
    action: String,
    textColor: Color,
    onCTA: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onCTA() },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            action.uppercase(),
            color = textColor,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonOutlineCTA(
    action: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .border(1.dp, MaterialTheme.colors.secondaryVariant, MaterialTheme.shapes.small)
    ) {
        Text(
            action.uppercase(),
            color = textColor,
            style = MaterialTheme.typography.button,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun AnnotatedClickableText(
    uriHandler: UriHandler,
    unclickableText: String,
    clickableText: String,
    url: String,
    modifier: Modifier = Modifier
) {
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

@Composable
fun WhiteSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            stringResource(id = R.string.why_take_doctolib),
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(32.dp)
        )
        ValueProposition(
            imageResource = R.drawable.ic_first_value,
            contentDescription = "first doctolib's image",
            description = stringResource(id = R.string.first_doctolib_advantage)
        )
        ValueProposition(
            imageResource = R.drawable.ic_second_value,
            contentDescription = "second doctolib's image",
            description = stringResource(id = R.string.second_doctolib_advantage)
        )
        ValueProposition(
            imageResource = R.drawable.ic_third_value,
            contentDescription = "third doctolib's image",
            description = stringResource(id = R.string.third_doctolib_advantage)
        )
        ValueProposition(
            imageResource = R.drawable.ic_fourth_value,
            contentDescription = "fourth doctolib's image",
            description = stringResource(id = R.string.fourth_doctolib_advantage)
        )
        Spacer(modifier = Modifier.size(32.dp))
        Text(
            stringResource(id = R.string.doctolib_is),
            style = MaterialTheme.typography.h5,
            color = Color.Black.copy(alpha = 0.5f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.size(32.dp))
        StatInfo(
            stat = stringResource(id = R.string.stat_patients),
            domain = stringResource(id = R.string.stat_patients_domain)
        )
        Spacer(modifier = Modifier.size(24.dp))
        StatInfo(
            stat = stringResource(id = R.string.stat_health_personal),
            domain = stringResource(id = R.string.stat_health_personal_domain)
        )
        Spacer(modifier = Modifier.size(24.dp))
        StatInfo(
            stat = stringResource(id = R.string.stat_positive_review),
            domain = stringResource(id = R.string.stat_positive_review_domain)
        )
        Spacer(modifier = Modifier.size(32.dp))
        PrivacyCard(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp, end = 24.dp))
        Spacer(modifier = Modifier.size(32.dp))
        DiscoverProSection()
        Spacer(modifier = Modifier.size(32.dp))
        RecruitSection(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
        Spacer(modifier = Modifier.size(32.dp))
        LegalSection()
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun PrivacyCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(red = 247, green = 250, blue = 255),
                        Color(red = 233, green = 246, blue = 252)
                    ),
                    500f,
                    800f
                )
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_privacy),
                contentDescription = "logo confidentalité",
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                stringResource(id = R.string.your_health_your_data),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                stringResource(id = R.string.your_health_your_data_body),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondaryVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.size(32.dp))
            ButtonCTA(
                action = stringResource(id = R.string.discover_engagements),
                textColor = MaterialTheme.colors.background,
                onCTA = { }
            )
            Spacer(modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun DiscoverProSection() {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            stringResource(id = R.string.health_pro),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.secondaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            stringResource(id = R.string.equip_doctolib),
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(id = R.string.free_medical_time),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(id = R.string.develop_activity),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(id = R.string.win_comfort),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            stringResource(id = R.string.improve_access),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.size(24.dp))
        ButtonOutlineCTA(
            action = stringResource(id = R.string.discover_doctolib_pro),
            textColor = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_join_pro),
            contentDescription = "Join Doctolib Pro",
            modifier = Modifier.padding(32.dp)
        )
    }
}

@Composable
fun RecruitSection(modifier: Modifier = Modifier) {
    Card(
        backgroundColor = Color(red = 249, green = 250, blue = 253),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(32.dp))
            Text(
                stringResource(id = R.string.doctolib_recruit),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.secondaryVariant,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                stringResource(id = R.string.better_health_system),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.secondaryVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            )
            Spacer(modifier = Modifier.size(32.dp))
            ButtonOutlineCTA(
                action = stringResource(id = R.string.join_us),
                textColor = MaterialTheme.colors.secondaryVariant
            )
            Spacer(modifier = Modifier.size(32.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_recruit),
                contentDescription = "Image recruitment"
            )
        }
    }
}

@Composable
fun LegalSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(id = R.string.legal_infos),
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(4.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_chevron_right_48px),
            contentDescription = "icon chevron",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariant),
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ValueProposition(
    imageResource: Int,
    contentDescription: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 32.dp, end = 32.dp)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = contentDescription
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            description,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.secondaryVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun StatInfo(stat: String, domain: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stat,
            style = MaterialTheme.typography.h5,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                domain,
                style = MaterialTheme.typography.caption,
                color = Color.Black.copy(alpha = 0.75f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(4.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_help_24px),
                contentDescription = "logo help",
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier.size(12.dp)
            )
        }
    }
}