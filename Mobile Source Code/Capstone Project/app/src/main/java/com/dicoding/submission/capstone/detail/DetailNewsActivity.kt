package com.dicoding.submission.capstone.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.submission.capstone.databinding.ActivityDetailNewsBinding
import com.dicoding.submission.capstone.model.DataItem
import com.google.gson.Gson

class DetailNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        supportActionBar?.title = "News"
        setContentView(binding.root)

        val newsJson = intent.getStringExtra("news")
        val newsItem = Gson().fromJson(newsJson, DataItem::class.java)

        // Use the `newsItem` to display the details in the UI
        binding.newsTitleTextView.text = newsItem.title
        binding.newsContentTextView.text = newsItem.content
        binding.newsImageView.loadImage(newsItem.imageUrl)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // Open the news URL in a web browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.newsLink))
                startActivity(intent)
            }
        }
        val spannableString = SpannableString("Read More!!!")
        spannableString.setSpan(
            clickableSpan,
            0,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.newsLinkTextView.text = spannableString
        binding.newsLinkTextView.movementMethod = LinkMovementMethod.getInstance()


    }
    private fun ImageView.loadImage(url: String) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}
