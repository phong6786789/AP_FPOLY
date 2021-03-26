package com.subi.apsubi.screen.news.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.subi.apsubi.BR
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
import com.subi.apsubi.data.base.fragment.BaseBindingFragment
import com.subi.apsubi.data.model.News
import com.subi.apsubi.databinding.FragmentNewsBinding
import com.subi.apsubi.screen.news.NewViewModel
import com.subi.apsubi.screen.news.NewsAdapter
import com.subi.apsubi.util.Constance
import kotlinx.android.synthetic.main.new_reader.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class NewsFragment1 : BaseBindingFragment<FragmentNewsBinding, NewViewModel>() {
    override val bindingVariable: Int
        get() = BR.newViewModel
    override val viewModel: NewViewModel
        get() = ViewModelProvider(this).get(NewViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_news

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        viewDataBinding?.rcvNews.apply {
            viewDataBinding?.rcvNews?.apply {
                adapter = NewsAdapter(viewModel.list1, onItemClick)
                hasFixedSize()
                layoutManager = LinearLayoutManager(context)
            }

        }
    }

    private val onItemClick = object : NewsAdapter.OnItemClickListener {

        override fun onClickScan(value: News) {
            loadDialog(value.link)

//            GlobalScope.launch {
//                val jsoup3 = Jsoup
//                    .connect(value.link)
//                    .cookie("laravel_session", HomeActivity.TOKEN)
//                    .get()
//
//                val link: Elements = jsoup3.select("div.kt-portlet__body")
//
//                var text = ""
//                for (x in link) {
//                    text += x.text().toString() + { R.string.new_line }
//                }
//                Handler(Looper.getMainLooper()).post(Runnable {
//                })
//
//
//            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDialog(text: String) {

        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.new_reader)
        dialog.setCancelable(false)
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
        dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

        val wedview: WebView = dialog.findViewById(R.id.wv_read_new)
        wedview.settings.javaScriptEnabled = true
        wedview.canGoBack()
        wedview.settings.userAgentString = "Chrome/89.0.4389.90"
        wedview.loadUrl(text)
        wedview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                dialog.loadingNew.visibility = View.GONE
                wedview.visibility = View.VISIBLE
            }
        }
        dialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }


}