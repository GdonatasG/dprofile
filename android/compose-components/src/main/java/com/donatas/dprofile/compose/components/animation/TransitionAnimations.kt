package com.donatas.dprofile.compose.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyleAnimated

@OptIn(ExperimentalAnimationApi::class)
object SlideFromSideTransition : DestinationStyleAnimated {
    override fun AnimatedContentTransitionScope<NavBackStackEntry>.enterTransition(): EnterTransition =
        SlideHorizontallyEnterTransition

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.exitTransition(): ExitTransition =
        slideOutHorizontally(
            targetOffsetX = { -it / 2 },
            animationSpec = tween(
                durationMillis = AnimationDuration,
                easing = FastOutSlowInEasing
            )
        ) + fadeOut(animationSpec = tween(AnimationDuration))

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popExitTransition(): ExitTransition =
        SlideHorizontalPopExitTransition

    override fun AnimatedContentTransitionScope<NavBackStackEntry>.popEnterTransition(): EnterTransition =
        slideInHorizontally(
            initialOffsetX = { -it / 2 },
            animationSpec = tween(
                durationMillis = AnimationDuration,
                easing = FastOutSlowInEasing
            )
        )
}

private const val AnimationDuration = 400

private val SlideHorizontallyEnterTransition = slideInHorizontally(
    initialOffsetX = { it / 2 },
    animationSpec = tween(
        durationMillis = AnimationDuration,
        easing = FastOutSlowInEasing
    )
) + fadeIn(animationSpec = tween(AnimationDuration / 2))

private val SlideHorizontalPopExitTransition = slideOutHorizontally(
    targetOffsetX = { it / 2 },
    animationSpec = tween(
        durationMillis = AnimationDuration,
        easing = FastOutSlowInEasing
    )
) + fadeOut(animationSpec = tween(AnimationDuration / 2))
