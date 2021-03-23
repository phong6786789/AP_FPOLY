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
        GlobalScope.launch {
            val jsoup = Jsoup
                .connect(Constance.DIEM_DANH)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val link: Elements = jsoup.select("div.kt-portlet")

            val titleName = link.select("div.kt-portlet__head")
            val table = link.select("tbody")

            var tit = ArrayList<String>()
            for (x in titleName) {
                var title = x.select("h3").text()
                tit.add(title)
            }
            var i = 0;
            for (x in table) {
                println("ahihi: ${tit[i]}")
                var row = x.select("tr")
                for (z in row) {
                    var cols = z.select("td")
                    var diemDanh = DiemDanh(
                        cols[0].text(),
                        cols[1].text(),
                        cols[2].text(),
                        cols[3].text(),
                        cols[4].text(),
                        cols[5].text(),
                        cols[6].text(),
                    )
                    println("ahihi: $diemDanh")

                }

                i++

            }
//            for (x in link) {
//                var title = x.select("h3").text()
//            }

        }


        return view
    }
}