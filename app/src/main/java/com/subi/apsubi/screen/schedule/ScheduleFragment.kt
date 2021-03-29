package com.subi.apsubi.screen.schedule

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialFadeThrough
import com.subi.apsubi.BR
import com.subi.apsubi.HomeActivity
import com.subi.apsubi.R
import com.subi.apsubi.data.base.fragment.BaseBindingFragment
import com.subi.apsubi.data.base.fragment.BaseFragment
import com.subi.apsubi.data.model.LichHoc
import com.subi.apsubi.data.model.News
import com.subi.apsubi.databinding.FragmentScheduleBinding
import com.subi.apsubi.screen.news.NewViewModel
import com.subi.apsubi.screen.news.NewsAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*
import kotlinx.android.synthetic.main.info_full.*
import kotlinx.android.synthetic.main.new_reader.*

class ScheduleFragment : BaseBindingFragment<FragmentScheduleBinding, ScheduleViewModel>() {
    override val bindingVariable: Int
        get() = BR.scheduleViewModel
    override val viewModel: ScheduleViewModel
        get() = ViewModelProvider(this).get(ScheduleViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_schedule

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
        viewDataBinding?.rcvSchedule?.apply {
            adapter = ScheduleAdapter(viewModel.list, onItemClick)
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }

    private val onItemClick = object : ScheduleAdapter.OnItemClickListener {

        override fun onClickScan(value: LichHoc) {
            loadDialog(value.mon, value.chitiet)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDialog(tit:String, texts: String) {

        val dialog = Dialog(requireActivity())
        dialog.setContentView(R.layout.info_full)
        dialog.setCancelable(false)
        val window = dialog.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
        dialog.window?.setWindowAnimations(R.style.Animation_Design_BottomSheetDialog)

        val title: TextView = dialog.findViewById(R.id.tvTitleSchedule)
        val text: TextView = dialog.findViewById(R.id.tvChiTiet)

        title.text = "CHI TIẾT"
        text.text = texts

        dialog.btnCloseSch.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}