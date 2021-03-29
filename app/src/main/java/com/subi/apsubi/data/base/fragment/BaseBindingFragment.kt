package com.subi.apsubi.data.base.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.subi.apsubi.data.base.viewmodel.BaseViewModel

abstract class BaseBindingFragment<V : ViewDataBinding, M : BaseViewModel> : BaseFragment<Any?, Any?>() {

    var viewDataBinding: V? = null

    abstract val bindingVariable: Int

    abstract val viewModel: M

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResource, container, false)
        initVariable(savedInstanceState, viewDataBinding?.root!!)
        initData(savedInstanceState, viewDataBinding?.root!!)
        return viewDataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding?.apply {
            setVariable(bindingVariable, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseBindingFragment
        }
    }
}
