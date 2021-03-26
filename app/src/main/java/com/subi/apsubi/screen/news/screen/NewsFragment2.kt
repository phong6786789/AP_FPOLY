package com.subi.apsubi.screen.news.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.apsubi.BR
import com.subi.apsubi.R
import com.subi.apsubi.data.base.fragment.BaseBindingFragment
import com.subi.apsubi.data.model.News
import com.subi.apsubi.databinding.FragmentNewsBinding
import com.subi.apsubi.screen.news.NewViewModel
import com.subi.apsubi.screen.news.NewsAdapter

class NewsFragment2 : BaseBindingFragment<FragmentNewsBinding, NewViewModel>() {
    override val bindingVariable: Int
        get() = BR.newViewModel
    override val viewModel: NewViewModel
        get() = ViewModelProvider(this).get(NewViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_news

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        viewDataBinding?.rcvNews.apply {
            viewDataBinding?.rcvNews?.apply {
                adapter = NewsAdapter(viewModel.list2, onItemClick)
                hasFixedSize()
                layoutManager = LinearLayoutManager(context)
            }

        }
    }
    private val onItemClick = object : NewsAdapter.OnItemClickListener {

        override fun onClickScan(value: News) {
            Toast.makeText(context, value.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }


}