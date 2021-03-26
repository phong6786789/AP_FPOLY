package com.subi.apsubi.screen.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.subi.apsubi.R
import com.subi.apsubi.screen.news.screen.NewsFragment1
import com.subi.apsubi.screen.news.screen.NewsFragment2
import com.subi.apsubi.screen.news.screen.NewsFragment3
import kotlinx.android.synthetic.main.fragment_new_pager.*
import kotlinx.android.synthetic.main.fragment_new_pager.view.*

class NewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            NewsFragment1(),
            NewsFragment2(),
            NewsFragment3()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )


        view.viewPager.adapter = adapter
        val tabLayout = view.tab_layout
        TabLayoutMediator(tabLayout, view.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Học tập"
                1 -> tab.text = "Hoạt động"
                2 -> tab.text = "Học phí"
            }
        }.attach()
        return view
    }

}