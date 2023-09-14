package com.example.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.ArticleItemBinding
import com.example.news.model.NewsArticle

class ArticleListAdapter(private val productList: ArrayList<NewsArticle>) : RecyclerView.Adapter<ArticleListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder) {
            with(productList[position]) {
                if (position == 0) {
                    holder.binding.articleSmallView.remove()
                    holder.binding.articleBigView.show()
                    urlToImage?.let { url ->
                        Glide.with(itemView).load(url).into(binding.articleBigView)
                    }
                } else {
                    binding.articleSmallView.show()
                    binding.articleBigView.remove()
                    urlToImage?.let { url ->
                        Glide.with(itemView).load(url).into(binding.articleSmallView)
                    }
                }
                binding.articleTitle.text = title
                binding.articleDate.text = relativePublishedAt
                holder.binding.root.setOnClickListener {
                    val intent = Intent(
                        holder.binding.root.context,
                        NewsArticleDetail::class.java,
                    )
                    intent.putExtra("myData", this)
                    holder.binding.root.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
