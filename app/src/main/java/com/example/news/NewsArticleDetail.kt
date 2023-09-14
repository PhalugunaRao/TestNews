package com.example.news

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.news.databinding.ArticleDetailsBinding
import com.example.news.model.NewsArticle

class NewsArticleDetail : AppCompatActivity() {
    private lateinit var binding: ArticleDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatus(this)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = DataBindingUtil.setContentView(this, R.layout.article_details)
        binding.lifecycleOwner = this
        val receivedDataObject = intent.getParcelableExtra<NewsArticle>("myData")
        if (receivedDataObject != null) {
            binding.articles = receivedDataObject
        }
        binding.share

        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, receivedDataObject?.url)
            startActivity(Intent.createChooser(intent, "Share Article"))
        }
    }
}
