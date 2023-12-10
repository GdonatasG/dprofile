package com.donatas.dprofile.composition.navigation.screens.components.skills

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.donatas.dprofile.compose.components.card.DCard
import com.donatas.dprofile.compose.components.text.SectionTitle
import com.donatas.dprofile.features.aboutme.skills.Skill
import com.donatas.dprofile.features.aboutme.skills.SkillsViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun SkillsView(model: SkillsViewModel) {
    val listState = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        state = listState
    ) {
        model.categories.forEachIndexed { index, category ->
            if (index > 0) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(bottom = 6.dp)
                ) {
                    SectionTitle(title = category.name)
                }
            }
            item {
                Spacer(modifier = Modifier.height(6.dp))
            }
            item(key = category.name) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    category.skills.forEach { skill ->
                        Skill(skill = skill)
                    }
                }
            }
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
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = skill.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W500)
            )
            Spacer(modifier = Modifier.height(10.dp))
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
    val inactiveColor: Color = Color.White.copy(alpha = 0.25f)
    val activeColor: Color = Color.White.copy(alpha = 0.95f)
    var backgroundColor = if (active) activeColor else inactiveColor

    Box(
        modifier = Modifier
            .width(18.dp)
            .height(6.dp)
            .background(backgroundColor)
    )
}
