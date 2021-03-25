package com.subi.apsubi.network

import com.subi.apsubi.HomeActivity
import com.subi.apsubi.data.model.*
import com.subi.apsubi.screen.home_fragment.HomeFragment
import com.subi.apsubi.util.Constance
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.select.Elements

object RetrofitData {
    val TOKEN_LARAVEL = "laravel_session"
    private val list: MutableMap<String, Elements> = mutableMapOf()
    var listBD: ArrayList<BangDiem> = ArrayList()
    var listDD: ArrayList<DiemDanh> = ArrayList()
    var listDTK: ArrayList<DiemTheoKy> = ArrayList()
    var listLH: ArrayList<LichHoc> = ArrayList()
    var listN1: ArrayList<News> = ArrayList()
    var listN2: ArrayList<News> = ArrayList()
    var listN3: ArrayList<News> = ArrayList()

    fun login() {
        val laravel_session = HomeActivity.TOKEN

        GlobalScope.launch {
            //Get điểm danh
            val jsoupDD = Jsoup
                .connect(Constance.DIEM_DANH)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkDD: Elements = jsoupDD.select("div.kt-portlet")
//            list["diem_danh"] = linkDD
            listDD.addAll(getDiemDanh(linkDD))

            //Get lịch học
            val jsoupUserx = Jsoup
                .connect(Constance.LICH_HOC)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserx: Elements = jsoupUserx.select("tbody")
//                list["lich_hoc"] = linkUserx
            listLH.addAll(getLichHoc(linkUserx))

            //Get điểm
            val jsoupUserd = Jsoup
                .connect(Constance.DIEM_THEO_KY)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserd: Elements = jsoupUserd.select("div.kt-portlet")
//                list["diem"] = linkUserd
            listDTK.addAll(getDiemTheoKy(linkUserd))

            //Get all điểm
            val jsoupUserAll = Jsoup
                .connect(Constance.BANG_DIEM)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserdAll: Elements = jsoupUserAll.select("div.kt-portlet")
//                list["allDiem"] = linkUserdAll
            listBD.addAll(getAllDiem(linkUserdAll))

            //Get thông tin cá nhân
            val jsoupUser = Jsoup
                .connect(Constance.USER)
                .cookie(TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUser: Elements = jsoupUser.select("input")
//                list["user"] = linkUser
            listBD.addAll(getAllDiem(linkUserdAll))

            val jsoup = Jsoup
                .connect(Constance.NEW1)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()
            //Get new
            val link: Elements = jsoup.select("div.kt-widget1__info")
//            list["new1"] = link
            listN1.addAll(getNews(link))

            listN2.clear()
            val jsoup2 = Jsoup
                .connect(Constance.NEW2)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()

            val link2: Elements = jsoup2.select("div.kt-widget1__info")
//            list["new2"] = link2
            listN2.addAll(getNews(link2))


            listN3.clear()
            val jsoup3 = Jsoup
                .connect(Constance.NEW3)
                .cookie(TOKEN_LARAVEL, laravel_session)
                .get()

            val link3: Elements = jsoup3.select("div.kt-widget1__info")
//            list["new3"] = link3
            listN3.addAll(getNews(link3))

            //Send
            HomeFragment().loadata_INTERNET(
                    listBD,
                    listDD,
                    listDTK,
                    listLH,
                    listN1,
                    listN2,
                    listN3
                )
        }


    }

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