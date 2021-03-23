package com.subi.apsubi.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.transition.MaterialFadeThrough
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
import com.subi.apsubi.data.model.DiemDanh
import com.subi.apsubi.network.RetrofitData
import com.subi.apsubi.util.Constance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialFadeThrough()
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)

//        val jsoupUser = Jsoup
//            .connect(Constance.USER)
//            .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
//            .get()
//        val linkUser: Elements = jsoupUser.select("div.kt-portlet")
////        for (x in linkUser){
////            println("ahihi: ${x.select("span").text()}")
////        }

        return view
    }
}