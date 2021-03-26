package com.subi.apsubi.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.subi.apsubi.data.model.News
import com.subi.apsubi.screen.news.NewsAdapter

object UtilBinding {
    @BindingAdapter("newsItem")
    @JvmStatic
    fun setItemNews(recyclerView: RecyclerView, items: List<News>?) {
        val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter
        if (adapter != null && adapter is NewsAdapter) {
            if (items != null) {
                adapter.setNewData(items)
            }
        }
    }
}