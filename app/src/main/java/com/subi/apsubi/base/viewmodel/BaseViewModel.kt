package com.subi.apsubi.base.viewmodel


import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    val compoDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compoDisposable.add(disposable)
    }

    fun activityDestroyed() {
        compoDisposable.clear()
    }

}