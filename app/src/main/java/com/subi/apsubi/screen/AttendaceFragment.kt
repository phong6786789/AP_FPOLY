package com.subi.apsubi.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.transition.MaterialFadeThrough
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
import kotlinx.android.synthetic.main.fragment_attendance.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class DiemDanhFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        enterTransition = MaterialFadeThrough()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_attendance, container, false)
        val tv: TextView = view.atend
        tv.text = HomeActivity.listDD.toString()
        return view
    }

}