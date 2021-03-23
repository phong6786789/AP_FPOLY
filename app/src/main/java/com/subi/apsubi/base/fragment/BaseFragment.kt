package com.subi.apsubi.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layoutResource: Int

    abstract fun initVariable(savedInstanceState: Bundle?, view: View)

    abstract fun initData(savedInstanceState: Bundle?, rootView: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(layoutResource, container, false)
        initVariable(savedInstanceState, rootView)
        initData(savedInstanceState, rootView)
        return rootView
    }
}
