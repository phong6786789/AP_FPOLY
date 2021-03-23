package com.subi.apsubi.screen.wedview

import android.app.ProgressDialog
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient


@Suppress("UNREACHABLE_CODE")
class WedViewController : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            view?.loadUrl(url)
        }
        return true
    }

    override fun onPageFinished(view: WebView?, url: String?) {
//        super.onPageFinished(view, url)
        val cookies = CookieManager.getInstance().getCookie(url)
        println("ahihi: $cookies")
    }
}