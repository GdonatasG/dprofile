package com.donatas.dprofile.compose.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyleAnimated

@OptIn(ExperimentalAnimationApi::class)
object SlideFromSideTransition : DestinationStyleAnimated {
    private const val ANIMATION_DURATION = 360

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { -it / 3 },
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { -it / 3 },
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        )

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION,
                easing = FastOutSlowInEasing
            )
        )

}
