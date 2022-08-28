package com.mishelk.talia_lior_wedding.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Present


class PresentAdapter(
    context: Context,
    var data: List<Present>,
    private var onItemSelectedListener: OnItemSelectedListener?) : RecyclerView.Adapter<PresentAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_present, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val present: Present = data[position]
        holder.tvTitle.text = present.title
        holder.tvDescription.text = present.description
        if (present.presentType == Present.PresentType.VOUCHER.id)
            holder.ivImage.setImageResource(R.drawable.gift)
        else if (present.presentType == Present.PresentType.VIDEO.id)
            holder.ivImage.setImageResource(R.drawable.ic_camera)

        holder.rlContent.setOnClickListener {
            onItemSelectedListener?.onItemSelected(data[position], position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        var rlContent: RelativeLayout = itemView.findViewById(R.id.rlContent)
    }

    interface OnItemSelectedListener {
        fun onItemSelected(item: Present, position: Int)
    }
}