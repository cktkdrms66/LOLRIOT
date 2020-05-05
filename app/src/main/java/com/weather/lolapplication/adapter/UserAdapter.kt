package com.weather.lolapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.weather.lolapplication.databinding.ItemUserBinding
import com.weather.lolapplication.room.entity.User

class UserAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var list : List<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding : ItemUserBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(list[position])
    }

    inner class UserViewHolder(val binding:ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user:User){
            binding.user = user
        }
    }

    fun updateList(list:List<User>){
        this.list = list
        notifyDataSetChanged()
    }
}