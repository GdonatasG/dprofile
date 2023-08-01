package com.donatas.dprofile.features.aboutme.roadtoprogramming.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.features.aboutme.roadtoprogramming.presentation.RoadToProgrammingViewModel

@Composable
fun RoadToProgrammingView(model: RoadToProgrammingViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = "I was always curious about computers during my teenage. " +
                        "So, my road to programming started with that curiosity. " +
                        "As I remember, my first written code was in the Pascal language. " +
                        "Sure, nothing fancy, just additions, subtractions, and other operations between numbers, " +
                        "but this is how my passion began.\n"
            )
        }
        item {
            Text(
                text = "Fortunately, that curiosity didn’t fade away and I got more deeply into programming" +
                        " by learning HTML, CSS, and C++ language at school. \n"
            )
        }
        item {
            Text(
                text = "You have probably heard that hard work pays off. And... It did!" +
                        " I hit 100 points on the information systems exam when I completed my secondary education.\n"
            )
        }
        item {
            Text(
                text = "However, passion and curiosity weren’t enough, sure, it was the key to continuing my road" +
                        " to programming (or already staying on that road). I had to learn how to be consistent," +
                        " and disciplined and set goals for myself to achieve better results in that field.\n"
            )
        }
        item {
            Text(
                text = "Looking at my road back, I think that I traveled and learned a lot. " +
                        "You might ask, did I finish my road to programming? No, " +
                        "I think there is no way to finish it because there is always where I can get better. " +
                        "This is why programming is interesting.\n"
            )
        }
        item {
            Text(
                text = "Currently, I’m working as a mobile applications developer and getting more and more" +
                        " experience every day. Still curious. Still passionate. Still disciplined. Still learning."
            )
        }
    }
}
