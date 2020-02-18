package com.app.basemodule.presentation.customview

import android.view.animation.Interpolator

/**
 * @author rahmatkhujaevs on 13/02/19
 * */
class BetterBounceInterpolator(private val amplitude: Double, private val frequency: Double) : Interpolator {

    override fun getInterpolation(input: Float): Float {
        return (-1 * Math.pow(Math.E, -input / amplitude) * Math.cos(frequency * input) + 1).toFloat()
    }
}