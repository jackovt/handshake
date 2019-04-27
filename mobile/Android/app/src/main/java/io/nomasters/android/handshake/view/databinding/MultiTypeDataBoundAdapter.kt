package io.nomasters.android.handshake.view.databinding

import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.ViewDataBinding
import io.nomasters.android.handshake.BR
import java.util.*

/**
 * @author JH431939 (Jack Hughes)
 * @since 4/26/19
 */
class MultiTypeDataBoundAdapter<T : LayoutBinding, V : ViewDataBinding>(
    val items: MutableList<T>,
    val actionCallback: ActionCallback?
) : BaseDataBoundAdapter<V>() {

    override fun bindItem(holder: DataBoundViewHolder<V>, position: Int, payloads: List<Any>) {
        holder.binding.setVariable(BR.data, items[position])
        // this will work even if the layout does not have a callback parameter
        holder.binding.setVariable(BR.callback, actionCallback)
    }

    @LayoutRes
    override fun getItemLayoutId(position: Int): Int {
        return getItem(position)?.getLayoutId() ?: -1
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun indexOf(item: T): Int {
        return items.indexOf(item)
    }

    fun addItem(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addItem(position: Int, item: T) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun addItems(vararg items: T) {
        val start = this.items.size
        Collections.addAll(this.items, *items)
        notifyItemRangeChanged(start, items.size)
    }

    fun removeItem(item: T) {
        val position = items.indexOf(item)
        if (position >= 0) {
            items.removeAt(position)
            notifyItemRemoved(position)
            itemRemoved(position)
        }
    }

    fun removeItems(vararg items: T) {
        val size = this.items.size
        this.items.removeAll(Arrays.asList(*items))
        notifyItemRangeChanged(0, size)
    }

    fun itemRemoved(position: Int) {
        // Convenience Method for extension
    }

    fun clear() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }


    @Nullable
    fun getItem(position: Int): T? {
        return if (position < items.size) items[position] else null
    }

    /**
     * Class that all action callbacks must extend for the adapter callback.
     */
    interface ActionCallback
}