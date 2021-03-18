package com.subi.apsubi.screen.campus

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.subi.apsubi.BR
import com.subi.apsubi.R
import com.subi.apsubi.base.fragment.BaseBindingFragment
import com.subi.apsubi.databinding.FragmentCampusBinding

class CampusFragment : BaseBindingFragment<FragmentCampusBinding, CampusViewModel>() {
    override val bindingVariable: Int
        get() = BR.campusSelect
    override val viewModel: CampusViewModel
        get() = ViewModelProvider(this).get(CampusViewModel::class.java)
    override val layoutResource: Int
        get() = R.layout.fragment_campus

    override fun initVariable(savedInstanceState: Bundle?, view: View) {
    }

    override fun initData(savedInstanceState: Bundle?, rootView: View) {
    }


}