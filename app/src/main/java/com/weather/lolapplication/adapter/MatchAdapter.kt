package com.weather.lolapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weather.lolapplication.R
import com.weather.lolapplication.data.Match
import com.weather.lolapplication.databinding.ItemLeagueBinding

class MatchAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var list: ArrayList<Match> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemLeagueBinding = ItemLeagueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MatchViewHolder).bind(list[position])
    }

    fun updateList(list:ArrayList<Match>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(val binding: ItemLeagueBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Match){
            binding.match = item
        }
    }

}