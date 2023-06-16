package com.dicoding.submission.capstone.ui.notifications

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submission.capstone.R
import com.dicoding.submission.capstone.model.User

class ProfileAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = View.inflate(parent.context, R.layout.fragment_notifications, null)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView1)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView1)

        fun bind(user: User) {
            usernameTextView.text = user.username
            emailTextView.text = user.email
        }
    }
}
