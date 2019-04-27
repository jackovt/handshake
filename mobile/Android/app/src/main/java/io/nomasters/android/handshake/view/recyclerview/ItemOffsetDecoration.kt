package io.nomasters.android.handshake.view.recyclerview

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class ItemOffsetDecoration(
    @NonNull context: Context,
    @DimenRes offsetStart: Float,
    @DimenRes offsetEnd: Float,
    @DimenRes offsetTop: Float,
    @DimenRes offsetBottom: Float
) : RecyclerView.ItemDecoration() {

    private val offsetPixelStart: Int
    private val offsetPixelEnd: Int
    private val offsetPixelTop: Int
    private val offsetPixelBottom: Int

    constructor(@NonNull context: Context, @DimenRes offsetX: Float, @DimenRes offsetY: Float) : this(
        context,
        offsetX,
        offsetX,
        offsetY,
        offsetY
    )

    constructor(@NonNull context: Context, @DimenRes offset: Float) : this(
        context,
        offset,
        offset,
        offset,
        offset
    )

    init {
        val resources = context.resources
        this.offsetPixelStart = if (offsetStart == 0F) 0 else offsetStart.toInt()
        this.offsetPixelEnd = if (offsetEnd == 0F) 0 else offsetEnd.toInt()
        this.offsetPixelTop = if (offsetTop == 0F) 0 else offsetTop.toInt()
        this.offsetPixelBottom = if (offsetBottom == 0F) 0 else offsetBottom.toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(
            offsetPixelStart,
            offsetPixelEnd,
            offsetPixelTop,
            offsetPixelBottom
        )
    }
}