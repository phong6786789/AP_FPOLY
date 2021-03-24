package com.subi.apsubi.network

import com.subi.apsubi.HomeActivity
import com.subi.apsubi.data.model.*
import com.subi.apsubi.util.Constance
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object RetrofitData {
    val TOKEN_LARAVEL = "laravel_session"
    private val list: MutableMap<String, Elements> = mutableMapOf()
    fun login() {
        try {
            //Token for all
            val laravel_session = HomeActivity.TOKEN

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
            val jsoupUserx = Jsoup
                .connect(Constance.LICH_HOC)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserx: Elements = jsoupUserx.select("tbody")
            list["lich_hoc"] = linkUserx

            //Get điểm
            val jsoupUserd = Jsoup
                .connect(Constance.DIEM_THEO_KY)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserd: Elements = jsoupUserd.select("div.kt-portlet")

            //Get all điểm
            val jsoupUserAll = Jsoup
                .connect(Constance.BANG_DIEM)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserdAll: Elements = jsoupUserAll.select("div.kt-portlet")

            //Get thông tin cá nhân
            val jsoupUser = Jsoup
                .connect(Constance.USER)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUser: Elements = jsoupUser.select("input")
            list["user"] = linkUser
//            for (x in linkUser) {
//                println("ahihi: ${x.attr("value")}")
//            }
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

    fun getDiemDanh(link: Elements) {
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

    fun getLichHoc(elements: Elements) {
        var i = 0
        for (x in elements) {
//            println("ahihi: ${tit[i]}")
            var row = x.select("tr")
            for (z in row) {
                var cols = z.select("td")
                var lichHoc = LichHoc(
                    cols[0].text(),
                    cols[1].text(),
                    cols[2].text(),
                    cols[3].text(),
                    cols[4].text(),
                    cols[5].text(),
                    cols[6].text(),
                    cols[7].text(),
                    cols[8].text(),
                    cols[9].text(),
                    cols[10].select("a").attr("data")
                )
                println("ahihi: $lichHoc")

            }

            i++

        }
    }

    fun getDiemTheoKy(linkUserd: Elements) {
        val title = linkUserd.select("div.kt-portlet__head")
        val table = linkUserd.select("tbody")
        val ketQua = linkUserd.select("tfoot")
        var tit = ArrayList<String>()
        tit.clear()
        for (x in title) {
            val title = x.select("h3").text()
            tit.add(title)
        }
        for (x in ketQua) {
            val title = x.text()
            tit.add(title)
        }
        var i = 0
        for (x in table) {
            println("ahihi: ${tit[i]}")
            var row = x.select("tr")
            for (z in row) {
                var cols = z.select("td")
                var diemTheoKy = DiemTheoKy(
                    cols[0].text(),
                    cols[1].text(),
                    cols[2].text(),
                    cols[3].text(),
                    cols[4].text()
                )
                println("ahihi: $diemTheoKy")
            }
            println("ahihi: ${tit[4 + i]}")
            println("ahihi: ----------------------------------------")

            i++

        }
    }

    fun getAllDiem(linkUserd: Elements) {
        try {
            val chuyenNganh = linkUserd.select("h3").text()
            val bang_diem = linkUserd.select("tbody")
            println("ahihi: $chuyenNganh")
            for (x in bang_diem) {
                var row = x.select("tr")
                for (z in row) {
                    var cols = z.select("td")
                    var bangDiem = BangDiem(
                        cols[0].text(),
                        cols[1].text(),
                        cols[2].text(),
                        cols[3].text(),
                        cols[4].text(),
                        cols[5].text(),
                        cols[6].text(),
                        cols[7].text(),
                        cols[8].text()
                    )
                    println("ahihi: $bangDiem")

                }
            }
        } catch (ex: java.lang.Exception) {

        }
    }
}