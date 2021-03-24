package com.subi.apsubi.screen.wedview

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
import kotlinx.android.synthetic.main.fragment_wed_view.view.*
import pl.droidsonroids.gif.GifImageView


class WedViewFragment : Fragment() {
    lateinit var wedview: WebView
    lateinit var layout: LinearLayout
    lateinit var text: TextView
    lateinit var loading: GifImageView
    val url = "https://ap.poly.edu.vn/login/google?campus_id=ps"

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
//        val url = arguments?.getString("url").toString()

        if (isNetworkAvailable()) {
//            Toast.makeText(context, "Có", Toast.LENGTH_SHORT).show()
            wedview.settings.javaScriptEnabled = true
            wedview.canGoBack()
            wedview.settings.userAgentString = "Chrome/89.0.4389.90"
            wedview.loadUrl(url)
            wedview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    loading.visibility = View.GONE
                    val cookies = CookieManager.getInstance().getCookie(url)
                    var laravel_session = cookies.substringAfterLast("laravel_session=", ";").trim()
                    //Check token
                    if (laravel_session.isEmpty() || !laravel_session.substring(0, 6)
                            .contains("laravel")
                    ) {
                        wedview.visibility = View.VISIBLE
                    }
                    if (laravel_session.length != 0) {
                        val bundle = bundleOf("token" to laravel_session)
                        HomeActivity.TOKEN = laravel_session
                        findNavController().navigate(
                            R.id.action_wedViewFragment_to_homeFragment,
                            bundle
                        )
                    }
                }
            }
        } else {
            Toast.makeText(context, "Không có INTERNET, load data from local!", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(
                R.id.action_wedViewFragment_to_homeFragment
            )
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}

