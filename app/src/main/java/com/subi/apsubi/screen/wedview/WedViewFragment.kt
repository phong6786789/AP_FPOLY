package com.subi.apsubi.screen.wedview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.subi.apsubi.R

class WedViewFragment : Fragment() {
    lateinit var wedview:WebView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_wed_view, container, false)
        wedview = view.findViewById(R.id.wv_login)

        settingWedView()
        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    private fun settingWedView() {
        val url = arguments?.getString("url").toString()
        wedview.settings.javaScriptEnabled = true
        wedview.canGoBack()
        wedview.webViewClient = WedViewController()
        wedview.settings.userAgentString = "Chrome/89.0.4389.90";
        wedview.loadUrl(url)
    }
}