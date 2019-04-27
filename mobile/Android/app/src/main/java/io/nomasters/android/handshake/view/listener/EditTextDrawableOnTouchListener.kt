package io.nomasters.android.handshake.view.listener

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.widget.TextView


/**
 * @author JH431939 (Jack Hughes)
 * @since 4/20/19
 */
open class EditTextDrawableOnTouchListener(
    private val view: TextView,
    private val drawablePosition: DrawablePosition
) :
    View.OnTouchListener {

    companion object {
        fun build(view: TextView, position: DrawablePosition): EditTextDrawableOnTouchListener {
            return EditTextDrawableOnTouchListener(view, position)
        }
    }

    enum class DrawablePosition(val position: Int) {
        DRAWABLE_LEFT(0),
        DRAWABLE_TOP(1),
        DRAWABLE_RIGHT(2),
        DRAWABLE_BOTTOM(3),
    }

    private var drawable: Drawable? = null
    private val fuzz = 10
    private var fuzzPx: Int

    init {
        val drawables = view.compoundDrawables
        if (drawables.size == 4)
            this.drawable = drawables[drawablePosition.position]
        fuzzPx = (view.context.resources.displayMetrics.density * fuzz).toInt()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (MotionEvent.ACTION_DOWN == event?.action && drawable != null && v != null) {
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            drawable?.let {
                val bounds = it.bounds
                if (isWithinRightBounds(x, v, bounds)
                    || isWithinTopBounds(y, v, bounds)
                ) {
                    return onDrawableTouch(event)
                }
            }

        }
        return false
    }

    private fun isWithinRightBounds(x: Int, v: View, bounds: Rect): Boolean {
        val rightSideOfDrawable = v.right - v.paddingRight
        return (drawablePosition == DrawablePosition.DRAWABLE_RIGHT
                && x >= rightSideOfDrawable - bounds.width() - fuzzPx
                && x <= rightSideOfDrawable + fuzzPx)
    }

    // TODO - finish for other bounds. Right now only care about right drawable
    private fun isWithinTopBounds(x: Int, v: View, bounds: Rect) =
        drawablePosition == DrawablePosition.DRAWABLE_TOP
                && false

    open fun onDrawableTouch(event: MotionEvent): Boolean {
        return false
    }
}