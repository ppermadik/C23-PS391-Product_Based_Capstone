package com.dicoding.submission.capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.capstone.data.datakota.Kota

class KotaAdapter(
    private val kotaList: List<Kota>,
    private val onItemClick: (Kota) -> Unit
) : RecyclerView.Adapter<KotaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kota, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kota = kotaList[position]
        holder.bind(kota)
        holder.itemView.setOnClickListener { onItemClick(kota) }
    }

    override fun getItemCount(): Int {
        return kotaList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val kotaImageView: ImageView = itemView.findViewById(R.id.img_item_kota)
        private val kotaNameTextView: TextView = itemView.findViewById(R.id.judul_kota)

        fun bind(kota: Kota) {
            Glide.with(itemView)
                .load(kota.photo)
                .into(kotaImageView)

            kotaNameTextView.text = kota.name
            itemView.setOnClickListener {
                onItemClick.invoke(kota)
            }
        }
    }
}
