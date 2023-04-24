package com.example.retrofit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.models.ImageRandom
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val mList: ArrayList<ImageRandom>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ContenedorDeVista>(){
    var innerdogs: ArrayList<ImageRandom> = mList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContenedorDeVista {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item, parent,false)
        return ContenedorDeVista(view)
    }
    override fun onBindViewHolder(holder: ContenedorDeVista, position: Int) {
        val dogs: ImageRandom = innerdogs.get(position)
        val setIMG = holder.imgItem
        Picasso.get().load(dogs.message).into(setIMG)
        holder.txtItem.text = dogs.message
    }
    override fun getItemCount(): Int {
        return innerdogs.size
    }
    inner class ContenedorDeVista(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val imgItem: ImageView
        val txtItem: TextView
        init {
            imgItem = view.findViewById(R.id.imgItem)
            txtItem = view.findViewById(R.id.txtItem)
        }
        override fun onClick(p0: View?) {
        }
    }
}