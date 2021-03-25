package com.subi.apsubi.screen.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.subi.apsubi.R
import com.subi.apsubi.data.model.*
import com.subi.apsubi.network.RetrofitData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.w3c.dom.Text

class HomeFragment : Fragment() {
    var tv:TextView?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialFadeThrough()
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        tv = view.findViewById(R.id.tvLoading)
        RetrofitData.login()

        return view
    }

    fun loadata_INTERNET(
        list1: ArrayList<BangDiem>,
        list2: ArrayList<DiemDanh>,
        list3: ArrayList<DiemTheoKy>,
        list4: ArrayList<LichHoc>,
        new1: ArrayList<News>,
        new2: ArrayList<News>,
        new3: ArrayList<News>
    ) {
        listBD = list1
        listDD = list2
        listDTK = list3
        listLH = list4
        listN1 = new1
        listN2 = new2
        listN3 = new3
        loadDone()
    }
    companion object {
        var listBD: ArrayList<BangDiem> = ArrayList()
        var listDD: ArrayList<DiemDanh> = ArrayList()
        var listDTK: ArrayList<DiemTheoKy> = ArrayList()
        var listLH: ArrayList<LichHoc> = ArrayList()
        var listN1: ArrayList<News> = ArrayList()
        var listN2: ArrayList<News> = ArrayList()
        var listN3: ArrayList<News> = ArrayList()
    }
    private fun loadDone() {
        println("ahihi: done")
    }
}