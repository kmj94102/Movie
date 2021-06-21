package com.example.movie.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie.R
import com.example.movie.databinding.CellCastInfoBinding
import com.example.movie.getStartImageUrl
import com.example.movie.network.CastInfo
import org.jetbrains.anko.backgroundColorResource

class CastInfoAdapter(private val context : Context, private val list: List<CastInfo>) : RecyclerView.Adapter<CastInfoAdapter.CastInfoViewHolder>() {

    inner class CastInfoViewHolder(val binding : CellCastInfoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastInfoViewHolder {
        return CastInfoViewHolder(CellCastInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CastInfoViewHolder, position: Int) {
        if(list[position].profile_path.isNullOrEmpty()){
            holder.binding.imgCast.backgroundColorResource = R.color.dark_color
        }else{
            Glide.with(context).load("${getStartImageUrl()}${list[position].profile_path}").centerCrop().into(holder.binding.imgCast)
        }
        holder.binding.txtActorName.text = list[position].name
        holder.binding.txtCastName.text = list[position].character

        holder.binding.txtActorName.isSelected = true
        holder.binding.txtCastName.isSelected = true
    }

    override fun getItemCount(): Int {
        return list.size
    }
}