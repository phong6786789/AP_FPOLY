package com.subi.apsubi.screen.wedview

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.HomeActivity.Companion.isConnect
import com.subi.apsubi.R
import com.subi.apsubi.network.RetrofitData
import com.subi.apsubi.util.Constance
import kotlinx.android.synthetic.main.fragment_wed_view.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import pl.droidsonroids.gif.GifImageView


class WedViewFragment : Fragment() {
    lateinit var wedview: WebView
    lateinit var layout: LinearLayout
    lateinit var text: TextView
    lateinit var loading: GifImageView
    var laravel_session = ""
    val url = "https://ap.poly.edu.vn/login/google?campus_id=ps"

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wed_view, container, false)
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
        if (isNetworkAvailable()) {
            isConnect = true
//            Toast.makeText(context, "Có", Toast.LENGTH_SHORT).show()
            wedview.settings.javaScriptEnabled = true
            wedview.canGoBack()
            wedview.settings.userAgentString = "Chrome/89.0.4389.90"
            wedview.loadUrl(url)
            wedview.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    val cookies = CookieManager.getInstance().getCookie(url)
                    laravel_session = cookies.substringAfterLast("laravel_session=", ";").trim()
                    println("ahihi: $laravel_session")
                    wedview.visibility = View.GONE
                    if (url != null) {
                        if (url.contains("https://ap.poly.edu.vn/feedback")) {
                            wedview.visibility = View.VISIBLE
                        }
                    }
                    if (url != null) {
                        if (!url.contains("https://ap.poly.edu.vn/sinh-vien")) {
                            loading.visibility = View.GONE
                            wedview.visibility = View.VISIBLE
                        }
                    }

                    if (url != null) {
                        if (url.contains("https://ap.poly.edu.vn/sinh-vien")) {
                            println("Login successfull")
                            wedview.visibility = View.GONE
                            HomeActivity.TOKEN = laravel_session
                            loadData(laravel_session)

                        }
                        else{
                            wedview.visibility = View.VISIBLE

                        }
                    }
                }
            }
        } else {
            isConnect = false
            Toast.makeText(
                context,
                "Không có INTERNET, load data from local!",
                Toast.LENGTH_SHORT
            )
                .show()
            findNavController().navigate(
                R.id.action_wedViewFragment_to_homeFragment
            )
        }
    }


    @UiThread
    private fun loadData(laravelSession: String) {
        println("ahihi: Load data...")
        val TOKEN_LARAVEL = "laravel_session"

        GlobalScope.launch {
            val jsoupDD = Jsoup
                .connect(Constance.DIEM_DANH)
                .cookie(TOKEN_LARAVEL, laravelSession)
                .get()
            val linkDD: Elements = jsoupDD.select("div.kt-portlet")
            HomeActivity.listDD.addAll(RetrofitData.getDiemDanh(linkDD))

            //Get lịch học
            val jsoupUserx = Jsoup
                .connect(Constance.LICH_HOC)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserx: Elements = jsoupUserx.select("tbody")
//                list["lich_hoc"] = linkUserx
            HomeActivity.listLH.addAll(RetrofitData.getLichHoc(linkUserx))

            //Get điểm
            val jsoupUserd = Jsoup
                .connect(Constance.DIEM_THEO_KY)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserd: Elements = jsoupUserd.select("div.kt-portlet")
//                list["diem"] = linkUserd
            HomeActivity.listDTK.addAll(RetrofitData.getDiemTheoKy(linkUserd))

            //Get all điểm
            val jsoupUserAll = Jsoup
                .connect(Constance.BANG_DIEM)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserdAll: Elements = jsoupUserAll.select("div.kt-portlet")
//                list["allDiem"] = linkUserdAll
            HomeActivity.listBD.addAll(RetrofitData.getAllDiem(linkUserdAll))

            //Get thông tin cá nhân
//            val jsoupUser = Jsoup
//                .connect(Constance.USER)
//                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
//                .get()
//            val linkUser: Elements = jsoupUser.select("input")
////                list["user"] = linkUser
//            HomeActivity.listBD.addAll(RetrofitData.getUser(linkUserdAll))

            val jsoup = Jsoup
                .connect(Constance.NEW1)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()
            //Get new
            val link: Elements = jsoup.select("div.kt-widget1__info")
//            list["new1"] = link
            HomeActivity.listN1.addAll(RetrofitData.getNews(link))

            HomeActivity.listN2.clear()
            val jsoup2 = Jsoup
                .connect(Constance.NEW2)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()

            val link2: Elements = jsoup2.select("div.kt-widget1__info")
            HomeActivity.listN2.addAll(RetrofitData.getNews(link2))


            HomeActivity.listN3.clear()
            val jsoup3 = Jsoup
                .connect(Constance.NEW3)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()

            val link3: Elements = jsoup3.select("div.kt-widget1__info")
//            list["new3"] = link3
            HomeActivity.listN3.addAll(RetrofitData.getNews(link3))

            //when done
            println("ahihi: derecting to homefragment")
            Handler(Looper.getMainLooper()).post(Runnable { //do stuff like remove view etc
                loading.visibility = View.GONE
                findNavController().navigate(R.id.action_wedViewFragment_to_homeFragment)

            })
        }


    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }


}

