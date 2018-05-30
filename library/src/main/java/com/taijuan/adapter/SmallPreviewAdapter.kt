package com.taijuan.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taijuan.ImagePicker
import com.taijuan.data.ImageItem
import com.taijuan.library.R
import kotlinx.android.synthetic.main.item_small_preview.view.*
import java.util.*

/**
 * Created by hubert
 *
 * Created on 2017/11/2.
 */
class SmallPreviewAdapter(private val mActivity: Activity, private var images: List<ImageItem> = ArrayList()) : RecyclerView.Adapter<SmallPreviewAdapter.SmallPreviewViewHolder>() {

    var current: ImageItem? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: OnItemClickListener? = null

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallPreviewViewHolder {
        return SmallPreviewViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SmallPreviewViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    inner class SmallPreviewViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_small_preview, parent, false)) {
        private var index: Int = 0
        private lateinit var data: ImageItem

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(index, data)
            }
        }

        fun bind(index: Int) {
            this.index = index
            data = images[index]
            itemView.v_frame.visibility = if (current == data) View.VISIBLE else View.GONE
            ImagePicker.imageLoader.displayImage(mActivity, data.path, itemView.iv_small, itemView.iv_small.width, itemView.iv_small.height)

        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, imageItem: ImageItem)
    }
}