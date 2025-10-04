package com.example.onlineshop.ui.component.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun AnimatedSlideIn(delay: Int = 0, durationMillis: Int = 1000, content: @Composable () -> Unit) {
    val isVisible = remember { MutableTransitionState(false) }.apply {
        targetState = true
    }
    AnimatedVisibility(
        visibleState = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -700 },
            animationSpec = tween(
                durationMillis = durationMillis,
                easing = FastOutSlowInEasing,
                delayMillis = delay
            )
        )
    ) {
        content()
    }
}