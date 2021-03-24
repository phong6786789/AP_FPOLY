package com.subi.apsubi.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
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
            //Get điểm
            val jsoupUserd = Jsoup
                .connect(Constance.DIEM_THEO_KY)
                .cookie(RetrofitData.TOKEN_LARAVEL, HomeActivity.TOKEN)
                .get()
            val linkUserd: Elements = jsoupUserd.select("div.kt-portlet")
            RetrofitData.getDiemTheoKy(linkUserd)
        }

        return view
    }
//    val result = withContext(Default) { work() }
}