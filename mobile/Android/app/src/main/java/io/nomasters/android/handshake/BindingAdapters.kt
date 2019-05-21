package io.nomasters.android.handshake

import android.annotation.SuppressLint
import android.text.method.LinkMovementMethod
import android.view.MotionEvent
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.nomasters.android.handshake.view.listener.EditTextDrawableOnTouchListener
import io.nomasters.android.handshake.view.recyclerview.ItemOffsetDecoration

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/20/19
 */
@SuppressLint("ClickableViewAccessibility")
@BindingAdapter("android:onClearEditTextListener")
fun setClearEditTextListener(textView: TextView, consumeTouch: Boolean) {
    textView.setOnTouchListener(object :
        EditTextDrawableOnTouchListener(textView, EditTextDrawableOnTouchListener.DrawablePosition.DRAWABLE_RIGHT) {
        override fun onDrawableTouch(event: MotionEvent): Boolean {
            textView.text = ""
            return consumeTouch
        }
    })
}

@BindingAdapter("android:htmlText")
fun setHtmlText(textView: TextView, htmlText: String) {
    textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
    textView.movementMethod = LinkMovementMethod.getInstance()
}

@BindingAdapter("onEditorActionListener")
fun setOnEditorActionListener(textView: TextView, listener: TextView.OnEditorActionListener) {
    textView.setOnEditorActionListener(listener)
}

@BindingAdapter("rawInputType")
fun setRawInputType(textView: TextView, rawInputType: Int) {
    textView.setRawInputType(rawInputType)
}

@BindingAdapter("adapter")
fun setRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    recyclerView.adapter = adapter
}

@BindingAdapter("spacingDecorator")
fun setSpacingDecorator(recyclerView: RecyclerView, offset: Float) {
    recyclerView.addItemDecoration(ItemOffsetDecoration(recyclerView.context, offset))
}

@BindingAdapter("verticalSpacingDecorator")
fun setVerticalSpacingDecorator(recyclerView: RecyclerView, verticalOffset: Float) {
    recyclerView.addItemDecoration(ItemOffsetDecoration(recyclerView.context, 0F, verticalOffset))
}

@BindingAdapter("horizontalSpacingDecorator")
fun setHorizontalSpacingDecorator(recyclerView: RecyclerView, horizontalOffset: Float) {
    recyclerView.addItemDecoration(ItemOffsetDecoration(recyclerView.context, horizontalOffset, 0F))
}

@BindingAdapter("bottomSpacingDecorator")
fun setBottomSpacingDecorator(recyclerView: RecyclerView, bottomOffset: Float) {
    recyclerView.addItemDecoration(ItemOffsetDecoration(recyclerView.context, 0F, 0F, 0F, bottomOffset))
}