package com.dicoding.submission.capstone.ui.dashboard

import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.submission.capstone.R
import com.dicoding.submission.capstone.detail.DetailNewsActivity
import com.dicoding.submission.capstone.model.DataItem
import com.google.gson.Gson

class NewsAdapter(private val newsList: MutableList<DataItem>):
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val username: TextView = itemView.findViewById(R.id.judul)
        private val image: ImageView = itemView.findViewById(R.id.img_item_photo)

        fun bind(item: DataItem) {
            username.text = item.title
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            val newsJson = Gson().toJson(newsList[position])
            intent.putExtra("news", newsJson)
            holder.itemView.context.startActivity(intent)
        }

    }
    fun setNewsList(list: List<DataItem>) {
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }


}