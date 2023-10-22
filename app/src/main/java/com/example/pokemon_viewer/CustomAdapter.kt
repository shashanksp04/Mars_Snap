package com.example.pokemon_viewer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CustomAdapter(val picList: MutableList<String>,val idList: MutableList<String>): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>()  {

    class CustomViewHolder(view:View): RecyclerView.ViewHolder(view){
        val marImage:ImageView
        var mar_date:TextView
        var mar_title:TextView

        init{
            marImage = view.findViewById(R.id.mars)
            mar_date = view.findViewById(R.id.date)
            mar_title = view.findViewById(R.id.Title_image)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mars_item,parent,false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return picList.size
    }

    override fun onBindViewHolder(holder: CustomAdapter.CustomViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(picList[position])
            .fitCenter()
            .into(holder.marImage)
        holder.mar_title.text = picList[position]
        holder.mar_date.text = idList[position]
    }
}