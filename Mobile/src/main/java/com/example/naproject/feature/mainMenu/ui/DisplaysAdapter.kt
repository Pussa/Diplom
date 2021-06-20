package com.example.naproject.feature.mainMenu.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.naproject.Display
import com.example.naproject.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.top_rockets_item.*
import kotlinx.android.synthetic.main.top_rockets_item.view.*

class DisplaysAdapter :
    ListAdapter<Display, DisplaysAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Display>() {

        override fun areItemsTheSame(oldItem: Display, newItem: Display): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Display, newItem: Display): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_rockets_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.DisplayOrComputerName.text = item.name
        holder.resolutionOrCPU.text = "Разрешение: " + item.resolution
        holder.frequencyOrStorage.text = "Частота: " + item.frequency.toString()
        holder.matrix_typeOrGPU.text = "Тип матрицы: " + item.matrix_type
        holder.sizeOrRAM.text = "Размер: " + item.size
        if(item.prices.isNotEmpty()){
            holder.prices.text = "Цены:\n"
            for(i in item.prices)
                holder.prices.append("${i.shop}     ${i.value}\n")
        }else{
            holder.prices.text ="Цены нет"
        }
        Glide.with(holder.itemView).load("http://pozdravko.ru:8000/${item.image}").into(holder.imagess.imagess)
        holder.find.text = "Ссылка:\n https://www.google.com/search?q=${item.name.replace(" ","+").replace("\"","")}"
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer


}