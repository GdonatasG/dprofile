package com.donatas.dprofile.features.aboutme.skills.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.card.DCard
import com.donatas.dprofile.features.aboutme.skills.presentation.Skill
import com.donatas.dprofile.features.aboutme.skills.presentation.SkillsViewModel

@Composable
fun SkillsView(model: SkillsViewModel) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Skill(skill = model.categories.first().skills.first())
        }
    }
}


@Composable
private fun Skill(skill: Skill) {
    DCard(
        shape = MaterialTheme.shapes.extraSmall.copy(
            topStart = ZeroCornerSize, bottomEnd = ZeroCornerSize
        )
    ) {
        Column(
            modifier = Modifier
                .width(130.dp)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = skill.value, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W500))
            LevelRow(level = skill.level, maxLevel = skill.maxLevel)
        }
    }
}

@Composable
private fun LevelRow(level: Int, maxLevel: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        repeat(maxLevel) { index ->
            val currentLevel = index + 1
            LevelItem(active = currentLevel <= level)
        }
    }
}

@Composable
private fun LevelItem(active: Boolean) {
    Box(
        modifier = Modifier
            .width(18.dp)
            .height(6.dp)
            .background(Color.White.copy(alpha = if (active) 0.95f else 0.25f))
    )
}
