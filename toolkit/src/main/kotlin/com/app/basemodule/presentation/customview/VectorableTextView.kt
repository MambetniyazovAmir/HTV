package com.app.basemodule.presentation.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatTextView
import com.app.basemodule.R
import com.app.basemodule.extensions.getDrawableFromVector

open class VectorableTextView : AppCompatTextView {
    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(context, attrs)
    }

    fun initAttrs(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attributeArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.VectorableTextView)

            var drawableLeft: Drawable? = null
            var drawableRight: Drawable? = null
            var drawableBottom: Drawable? = null
            var drawableTop: Drawable? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawableLeft = attributeArray.getDrawable(R.styleable.VectorableTextView_drawableLeftCompat)
                drawableRight = attributeArray.getDrawable(R.styleable.VectorableTextView_drawableRightCompat)
                drawableBottom = attributeArray.getDrawable(R.styleable.VectorableTextView_drawableBottomCompat)
                drawableTop = attributeArray.getDrawable(R.styleable.VectorableTextView_drawableTopCompat)
            } else {
                val drawableLeftId = attributeArray.getResourceId(
                        R.styleable.VectorableTextView_drawableLeftCompat, -1)
                val drawableRightId = attributeArray.getResourceId(
                        R.styleable.VectorableTextView_drawableRightCompat, -1)
                val drawableBottomId = attributeArray.getResourceId(
                        R.styleable.VectorableTextView_drawableBottomCompat, -1)
                val drawableTopId = attributeArray.getResourceId(
                        R.styleable.VectorableTextView_drawableTopCompat, -1)

                if (drawableLeftId != -1)
                    drawableLeft = context.getDrawableFromVector(drawableLeftId)
                if (drawableRightId != -1)
                    drawableRight = context.getDrawableFromVector(drawableRightId)
                if (drawableBottomId != -1)
                    drawableBottom = context.getDrawableFromVector(drawableBottomId)
                if (drawableTopId != -1)
                    drawableTop = context.getDrawableFromVector(drawableTopId)
            }
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight,
                    drawableBottom)
            attributeArray.recycle()
        }
    }
}
