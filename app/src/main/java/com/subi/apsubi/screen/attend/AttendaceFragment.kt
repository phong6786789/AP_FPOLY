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
import com.subi.apsubi.data.base.fragment.BaseBindingFragment
import com.subi.apsubi.databinding.FragmentAttendanceBinding
import com.subi.apsubi.screen.attend.AttendViewModel
import kotlinx.android.synthetic.main.fragment_attendance.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class DiemDanhFragment : BaseBindingFragment<FragmentAttendanceBinding, AttendViewModel>() {
    override val bindingVariable: Int
        get() = TODO("Not yet implemented")
    override val viewModel: AttendViewModel
        get() = TODO("Not yet implemented")
    override val layoutResource: Int
        get() = TODO("Not yet implemented")

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        TODO("Not yet implemented")

        val list = HomeActivity.listDD.toString()

    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
        TODO("Not yet implemented")
    }


}