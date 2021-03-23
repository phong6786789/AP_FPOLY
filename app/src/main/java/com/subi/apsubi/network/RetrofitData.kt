package com.subi.apsubi.network

import com.subi.apsubi.HomeActivity
import com.subi.apsubi.data.model.DiemDanh
import com.subi.apsubi.data.model.News
import com.subi.apsubi.util.Constance
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object RetrofitData {
    val TOKEN_LARAVEL = "laravel_session"
    private val list: MutableMap<String, Elements> = mutableMapOf()
    fun getThongTinHocTap(laravel_session: String) {
        try {
            //Get New 1
            val job = GlobalScope.launch {
                val listN1 = ArrayList<News>()
                listN1.clear()
                val jsoup = Jsoup
                    .connect(Constance.NEW1)
                    .cookie(TOKEN_LARAVEL, laravel_session)
                    .get()
                val link: Elements = jsoup.select("div.kt-widget1__info")
                list["new1"] = link

                val listN2 = ArrayList<News>()
                listN2.clear()
                val jsoup2 = Jsoup
                    .connect(Constance.NEW2)
                    .cookie(TOKEN_LARAVEL, laravel_session)
                    .get()

                val link2: Elements = jsoup2.select("div.kt-widget1__info")
                list["new2"] = link2


                val listN3 = ArrayList<News>()
                listN3.clear()
                val jsoup3 = Jsoup
                    .connect(Constance.NEW3)
                    .cookie(TOKEN_LARAVEL, laravel_session)
                    .get()

                val link3: Elements = jsoup3.select("div.kt-widget1__info")
                list["new3"] = link3


            }

            //Get điểm danh
            val jsoupDD = Jsoup
                .connect(Constance.DIEM_DANH)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkDD: Elements = jsoupDD.select("div.kt-portlet")
            list["diem_danh"] = linkDD

            //Get lịch học

            //Get điểm

            //Get thông tin cá nhân


            //when complete
            runBlocking {
                job.join()
                return@runBlocking list
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }


    private fun getNews(elements: Elements): ArrayList<News> {
        var list = ArrayList<News>()
        for (x in elements) {
            val a = x.select("span").text()
            val nguoiDang = a.substringAfterLast("Người đăng: ").substringBefore("Thời gian")
            val thoiGianDang = a.substringAfterLast("Thời gian: ").substringBefore(" Cập")
            val title = x.select("a").text()
            val link = x.select("a").first().absUrl("href")
            list.add(News(title, link, nguoiDang, thoiGianDang))
        }
        return list
    }

    fun <R> CoroutineScope.executeAsyncTask(
        onPreExecute: () -> Unit,
        doInBackground: () -> R,
        onPostExecute: (R) -> Unit
    ) = launch {
        onPreExecute()
        val result =
            withContext(Dispatchers.IO) { // runs in background thread without blocking the Main Thread
                doInBackground()
            }
        onPostExecute(result)
    }

    fun getDiemDanh(link: Elements){
        val titleName = link.select("div.kt-portlet__head")
        val table = link.select("tbody")

        var tit = ArrayList<String>()
        for (x in titleName) {
            var title = x.select("h3").text()
            tit.add(title)
        }
        var i = 0
        for (x in table) {
//            println("ahihi: ${tit[i]}")
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
//                println("ahihi: $diemDanh")

            }

            i++

        }
    }
}