package com.donatas.dprofile.features.aboutme.education.presentation

import com.donatas.dprofile.viewmodel.ViewModel

class EducationViewModel: ViewModel() {
    val timeline: List<TimelineItem> = listOf(
        TimelineItem(
            title = "BSc in Computer Information Systems",
            subtitle = "2019 - 2023",
            description = "Learned core skills of programming, security and mathematics",
            location = "Vytautas Magnus University"
        ),
        TimelineItem(
            title = "Java development",
            subtitle = "2018 (3 mos.)",
            description = "Learned Java development, OOP, SQL and Android Development basics",
            location = "Kaunas Coding School"
        ),
        TimelineItem(
            title = "Secondary education",
            subtitle = "2014 - 2018",
            description = "Completed secondary education in Marijampoles Suduvos gymnasium",
            location = "Marijampoles Suduvos gymnasium"
        )
    )
}
