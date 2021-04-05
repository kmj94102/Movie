package com.example.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R

class TrendingAdapter(private val context: Context, private val list : List<String>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {
    inner class TrendingViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cell_trending, parent, false))
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        Glide.with(context).load(list[position]).centerCrop().into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}