package com.example.news

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.databinding.MainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainScreenBinding
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatus(this)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        binding = DataBindingUtil.setContentView(this, R.layout.main_screen)
        binding.lifecycleOwner = this
        val viewModel: MyViewModel by viewModels()
        viewModel.getArticleViewModel()
        viewModel.articleListData.observe(
            this,
            Observer { data ->
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
                    binding.recyclerView.adapter = data.body()?.articles?.let { ArticleListAdapter(it) }
                }
            },
        )
    }
}
