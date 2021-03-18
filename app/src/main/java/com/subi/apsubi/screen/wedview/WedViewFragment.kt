package com.subi.apsubi.screen.wedview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.subi.apsubi.R
import kotlinx.android.synthetic.main.fragment_wed_view.view.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView


class WedViewFragment : Fragment() {
    lateinit var wedview: WebView
    lateinit var layout: LinearLayout
    lateinit var text: TextView
    lateinit var loading: GifImageView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_wed_view, container, false)
        wedview = view.wv_login
        layout = view.lnLayout
        text = view.textTest
        loading = view.loading

        settingWedView()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun settingWedView() {
        val url = arguments?.getString("url").toString()
        wedview.settings.javaScriptEnabled = true
        wedview.canGoBack()
//        wedview.webViewClient = WedViewController()
        wedview.settings.userAgentString = "Chrome/89.0.4389.90";
        wedview.loadUrl(url)
        wedview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loading.visibility = View.GONE
                wedview.visibility = View.VISIBLE
                val cookies = CookieManager.getInstance().getCookie(url)

            }
        }
    }
}