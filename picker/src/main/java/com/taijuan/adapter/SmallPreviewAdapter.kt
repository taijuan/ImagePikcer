package com.taijuan.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taijuan.ImagePicker
import com.taijuan.data.ImageItem
import com.taijuan.picker.R
import kotlinx.android.synthetic.main.item_small_preview.view.*
import java.util.*
import kotlin.properties.Delegates

internal class SmallPreviewAdapter(private val mActivity: Activity, private val images: List<ImageItem> = ArrayList()) : androidx.recyclerview.widget.RecyclerView.Adapter<SmallPreviewAdapter.SmallPreviewViewHolder>() {

    var current: ImageItem? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: ((imageItem: ImageItem) -> Unit)? = null

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallPreviewViewHolder {
        return SmallPreviewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SmallPreviewViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemId(position: Int): Long = position.toLong()

    inner class SmallPreviewViewHolder(parent: ViewGroup) : androidx.recyclerview.widget.RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_small_preview, parent, false)) {
        private var data: ImageItem by Delegates.notNull()

        init {
            itemView.setOnClickListener {
                listener?.invoke(data)
            }
        }

        fun bind(data: ImageItem) {
            this.data = data
            itemView.v_frame.visibility = if (current == data) View.VISIBLE else View.GONE
            ImagePicker.imageLoader.displayImage(mActivity, data.path, itemView.iv_small, itemView.iv_small.width, itemView.iv_small.height)
        }
    }
}