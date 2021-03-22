package com.subi.apsubi.network

import com.subi.apsubi.data.model.News
import com.subi.apsubi.util.Constance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class RetrofitData(token: String) {
    var token = token
    fun getThongTinHocTap(): ArrayList<News> {
        var list: ArrayList<News> = ArrayList<News>()
        var elements: Elements? = getData(Constance.NEW1)?.select("a[href]")


        if (elements != null) {
            for (x in elements) {

            }

        }
        return list
    }


    private fun getData(url: String): Document? {
        var jsoup: Document? = null
        GlobalScope.launch {
            jsoup = Jsoup
                .connect(Constance.BASE_URL)
                .cookie("laravel_session", token)
                .get()
        }

        return jsoup
    }
}