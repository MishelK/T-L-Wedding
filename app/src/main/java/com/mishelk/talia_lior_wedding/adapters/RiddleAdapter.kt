package com.mishelk.talia_lior_wedding.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mishelk.talia_lior_wedding.R
import com.mishelk.talia_lior_wedding.data_classes.Riddle
import java.util.*


class RiddleAdapter(
    context: Context,
    var data: List<Riddle>,
    private var onItemSelectedListener: OnItemSelectedListener?) : RecyclerView.Adapter<RiddleAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_riddle, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val riddle: Riddle = data[position]
        holder.tvTitle.text = riddle.title
        holder.tvDescription.text = riddle.description

        if (riddle.isSolved)
            holder.ivImage.setImageResource(R.drawable.ic_check)
        else
            holder.ivImage.setImageResource(R.drawable.search)

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
        fun onItemSelected(item: Riddle, position: Int)
    }
}