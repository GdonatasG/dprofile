package com.donatas.dprofile.composition.navigation.screens.components.road_to_programming

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donatas.dprofile.compose.components.AppDivider
import com.donatas.dprofile.compose.theme.secondaryTextColorDark
import com.donatas.dprofile.compose.theme.secondaryTextColorLight
import com.donatas.dprofile.features.aboutme.roadtoprogramming.RoadToProgrammingViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoadToProgrammingView(model: RoadToProgrammingViewModel) {
    val textStyle = MaterialTheme.typography.bodyMedium

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        stickyHeader {
            Heading(title = "The beginning")
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "I was always curious about computers during my teenage. " + "So, my road to programming started with that curiosity. " + "As I remember, my first written code was in the Pascal language. " + "Sure, nothing fancy, just additions, subtractions, and other operations between numbers, " + "but this is how my passion began.\n",
                    style = textStyle
                )
            }
        }
        stickyHeader {
            Heading(title = "Curiosity continues")
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Fortunately, that curiosity didn’t fade away and I got more deeply into programming" + " by learning HTML, CSS, and C++ language at school. \n",
                    style = textStyle
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "You have probably heard that hard work pays off. And... It did!" + " I hit 100 points on the information systems exam when I completed my secondary education.\n",
                    style = textStyle
                )
            }
        }
        stickyHeader {
            Heading(title = "The consistency")
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "However, passion and curiosity weren’t enough, sure, it was the key to continuing my road" + " to programming (or already staying on that road). I had to learn how to be consistent," + " and disciplined and set goals for myself to achieve better results in that field.\n",
                    style = textStyle
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Looking at my road back, I think that I traveled and learned a lot. " + "You might ask, did I finish my road to programming? No, " + "I think there is no way to finish it because there is always where I can get better. " + "This is why programming is interesting.\n",
                    style = textStyle
                )
            }
        }
        stickyHeader {
            Heading(title = "Still...")
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Currently, I’m working as a mobile applications developer and getting more and more" + " experience every day. Still curious. Still passionate. Still disciplined. Still learning.",
                    style = textStyle
                )
            }
        }
    }
}

@Composable
private fun Heading(title: String) {
    val secondaryColor = if (isSystemInDarkTheme()) secondaryTextColorDark else secondaryTextColorLight
    val textStyle = MaterialTheme.typography.bodyMedium.copy(color = secondaryColor, fontSize = 16.sp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
        ) {
            AppDivider(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                style = textStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(12.dp))
            AppDivider(modifier = Modifier.weight(1f))
        }
    }
}
