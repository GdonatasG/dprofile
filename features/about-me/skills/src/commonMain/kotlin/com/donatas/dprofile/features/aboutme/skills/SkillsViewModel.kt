package com.donatas.dprofile.features.aboutme.skills

import com.donatas.dprofile.viewmodel.ViewModel

class SkillsViewModel : ViewModel() {
    val categories: List<Category> = listOf(
        Category(
            name = "Programming languages", skills = listOf(
                Skill(
                    value = "Kotlin", level = 4
                ),
                Skill(
                    value = "Dart", level = 4
                ),
                Skill(
                    value = "Java", level = 3
                ),
            )
        ),
        Category(
            name = "Mobile development", skills = listOf(
                Skill(
                    value = "Kotlin Multiplatform", level = 4
                ),
                Skill(
                    value = "Jetpack Compose", level = 4
                ),
                Skill(
                    value = "Flutter", level = 4
                ),
            )
        ), Category(
            name = "Database", skills = listOf(
                Skill(
                    value = "SQL", level = 3
                ), Skill(
                    value = "NoSQL", level = 3
                ), Skill(
                    value = "MySQL", level = 3
                )
            )
        ), Category(
            name = "API & Web development", skills = listOf(
                Skill(
                    value = "Spring Boot", level = 3
                ), Skill(
                    value = "HTML", level = 3
                ), Skill(
                    value = "CSS", level = 3
                )
            )
        ),
        Category(
            name = "Version Control System", skills = listOf(
                Skill(
                    value = "Github", level = 4
                ), Skill(
                    value = "Gitlab", level = 4
                ), Skill(
                    value = "Bitbucket", level = 3
                )
            )
        ), Category(
            name = "IDE", skills = listOf(
                Skill(
                    value = "Android Studio", level = 4
                ), Skill(
                    value = "IntelliJ IDEA", level = 3
                )
            )
        ), Category(
            name = "Design & Prototyping", skills = listOf(
                Skill(
                    value = "Figma", level = 3
                )
            )
        )
    )
}
