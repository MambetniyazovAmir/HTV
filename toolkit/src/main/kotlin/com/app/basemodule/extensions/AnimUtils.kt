package com.app.basemodule.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.app.basemodule.presentation.customview.BetterBounceInterpolator
val TEXT_SMOOTH_ANIMTION_COEF = 1
fun animateColor(
    context: Context,
    target: Any?,
    propertyName: String,
    @ColorRes colorFrom: Int,
    @ColorRes colorTo: Int,
    duration: Int = 400,
    callback: () -> Unit
) {
    val anim = ObjectAnimator.ofObject(target, propertyName,
            ArgbEvaluator(), ContextCompat.getColor(context, colorFrom),
            ContextCompat.getColor(context, colorTo))
    anim.duration = duration.toLong()
    anim.addListener(animOnFinish { callback.invoke() })
    anim.start()
}

fun animOnFinish(callback: () -> Unit): AnimatorListenerAdapter {
    return object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            super.onAnimationEnd(animation)
            callback.invoke()
        }
    }
}

fun View.animTopToCenter(callback: () -> Unit): View {
    translationY = -50f
    alpha = 0f
    animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(400L * TEXT_SMOOTH_ANIMTION_COEF)
            .setInterpolator(FastOutSlowInInterpolator())
            .withEndAction { callback.invoke() }
            .start()
    return this
}

fun View.animLeftToCenter(callback: () -> Unit): View {
    translationX = -50f
    alpha = 0f
    animate()
            .translationX(0f)
            .alpha(1f)
            .setDuration(400L * TEXT_SMOOTH_ANIMTION_COEF)
            .setInterpolator(FastOutSlowInInterpolator())
            .withEndAction { callback.invoke() }
            .start()
    return this
}

fun View.animRightToCenter(callback: () -> Unit): View {
    translationX = 50f
    alpha = 0f
    animate()
            .translationX(0f)
            .alpha(1f)
            .setDuration(400L * TEXT_SMOOTH_ANIMTION_COEF)
            .setInterpolator(FastOutSlowInInterpolator())
            .withEndAction { callback.invoke() }
            .start()
    return this
}

fun View.animBottomToCenter(callback: () -> Unit): View {
    translationY = 50f
    alpha = 0f
    animate()
            .translationY(0f)
            .alpha(1f)
            .setDuration(400L * TEXT_SMOOTH_ANIMTION_COEF)
            .setInterpolator(FastOutSlowInInterpolator())
            .withEndAction { callback.invoke() }
            .start()
    return this
}

fun View.scaleWithBounceToLarge(duration: Long = 1500L) {
    val scale = ScaleAnimation(0f, 1f, 0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    val alpha = AlphaAnimation(0f, 1f)
    val set = AnimationSet(true)
    set.addAnimation(scale)
    set.addAnimation(alpha)
    set.interpolator = BetterBounceInterpolator(0.1, 30.0)
    set.duration = duration
    startAnimation(set)
}

fun View.scaleWithBounceFromLarge(duration: Long = 1000L) {
    val scale = ScaleAnimation(3f, 1f, 3f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    val alpha = AlphaAnimation(0f, 1f)
    val set = AnimationSet(true)
    set.addAnimation(scale)
    set.addAnimation(alpha)
    set.interpolator = BetterBounceInterpolator(0.1, 15.0)
    set.duration = duration
    startAnimation(set)
}

/**
animator.apply {
addListener(animOnFinish { /*some action*/ })
}
 */

@Suppress("unused")
fun animOnStart(callback: () -> Unit): AnimatorListenerAdapter {
    return object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            super.onAnimationStart(animation)
            callback.invoke()
        }
    }
}

/**
animator.apply {
addListener(animOnStart { /*some action*/ })
}
 */

fun transitionListener(transitionStart: () -> Unit, transitionEnd: () -> Unit): Transition.TransitionListener {
    return object : Transition.TransitionListener {
        override fun onTransitionResume(transition: Transition?) {
        }

        override fun onTransitionPause(transition: Transition?) {
        }

        override fun onTransitionCancel(transition: Transition?) {
        }

        override fun onTransitionStart(transition: Transition?) {
            transitionStart.invoke()
        }

        override fun onTransitionEnd(transition: Transition?) {
            transitionEnd.invoke()
        }
    }
}
