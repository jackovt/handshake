package io.nomasters.android.handshake.view.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class DataBoundViewHolder<T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun <T : ViewDataBinding> create(
            parent: ViewGroup,
            @LayoutRes layoutId: Int
        ): DataBoundViewHolder<T> {
            val binding = DataBindingUtil.inflate<T>(
                LayoutInflater.from(parent.context),
                layoutId, parent, false
            )
            return DataBoundViewHolder(binding)
        }
    }
}