package com.subi.apsubi.screen.news

import com.subi.apsubi.HomeActivity
import com.subi.apsubi.data.base.viewmodel.BaseViewModel
import com.subi.apsubi.data.model.News

class NewViewModel :BaseViewModel(){
    var list1: List<News> = HomeActivity.listN1
    var list2: List<News> = HomeActivity.listN2
    var list3: List<News> = HomeActivity.listN3
}