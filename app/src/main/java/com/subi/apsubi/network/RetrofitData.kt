package com.subi.apsubi.network

import com.subi.apsubi.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.select.Elements

object RetrofitData {
    fun getUser(elements: Elements) {
        for (x in elements) {
//            println("ahihi: ${x.attr("value")}")
        }
    }

    fun getNews(elements: Elements): ArrayList<News> {
        val list = ArrayList<News>()
        for (x in elements) {
            val a = x.select("span").text()
            val nguoiDang = a.substringAfterLast("Người đăng: ").substringBefore("Thời gian")
            val thoiGianDang = a.substringAfterLast("Thời gian: ").substringBefore(" Cập")
            val title = x.select("a").text()
            val link = x.select("a").first().absUrl("href")
            val news = News(title, link, nguoiDang, thoiGianDang)
//            println("ahihi: ${news}")
            list.add(news)
        }
        return list
    }


    fun getDiemDanh(link: Elements): ArrayList<DiemDanh> {
        val list = ArrayList<DiemDanh>()
        val titleName = link.select("div.kt-portlet__head")
        val table = link.select("tbody")

        val tit = ArrayList<String>()
        for (x in titleName) {
            val title = x.select("h3").text()
            tit.add(title)
        }
        var i = 0
        for (x in table) {
//            println("ahihi: ${tit[i]}")
            list.add(DiemDanh(tit[i], "", "", "", "", "", ""))
            val row = x.select("tr")
            for (z in row) {
                val cols = z.select("td")
                val diemDanh = DiemDanh(
                    cols[0].text(),
                    cols[1].text(),
                    cols[2].text(),
                    cols[3].text(),
                    cols[4].text(),
                    cols[5].text(),
                    cols[6].text(),
                )
                list.add(diemDanh)
//                println("ahihi: $diemDanh")
            }
            i++
        }
        return list
    }

    fun getLichHoc(elements: Elements): ArrayList<LichHoc> {
        val list = ArrayList<LichHoc>()
        for (x in elements) {
            val row = x.select("tr")
            for (z in row) {
                val cols = z.select("td")
                val lichHoc = LichHoc(
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
                list.add(lichHoc)
            }
        }
        return list
    }

    fun getDiemTheoKy(linkUserd: Elements): ArrayList<DiemTheoKy> {
        var list = ArrayList<DiemTheoKy>()
        val title = linkUserd.select("div.kt-portlet__head")
        val table = linkUserd.select("tbody")
        val ketQua = linkUserd.select("tfoot")
        val tit = ArrayList<String>()
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
//            println("ahihi: ${tit[i]}")
            list.add(DiemTheoKy(tit[i], "", "", "", ""))
            val row = x.select("tr")
            for (z in row) {
                val cols = z.select("td")
                val diemTheoKy = DiemTheoKy(
                    cols[0].text(),
                    cols[1].text(),
                    cols[2].text(),
                    cols[3].text(),
                    cols[4].text()
                )
                list.add(diemTheoKy)
//                println("ahihi: $diemTheoKy")
            }
//            println("ahihi: ${tit[4 + i]}")
//            println("ahihi: ----------------------------------------")

            i++

        }
        return list
    }

    fun getAllDiem(linkUserd: Elements): ArrayList<BangDiem> {
        var list = ArrayList<BangDiem>()
        try {
            val chuyenNganh = linkUserd.select("h3").text()
            val bang_diem = linkUserd.select("tbody")
//            println("ahihi: $chuyenNganh")
            list.add(
                BangDiem(chuyenNganh, "", "", "", "", "", "", "", "")
            )
            for (x in bang_diem) {
                val row = x.select("tr")
                for (z in row) {
                    val cols = z.select("td")
                    val bangDiem = BangDiem(
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
                    list.add(bangDiem)
//                    println("ahihi: $bangDiem")

                }
            }
            return list
        } catch (ex: java.lang.Exception) {

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
}