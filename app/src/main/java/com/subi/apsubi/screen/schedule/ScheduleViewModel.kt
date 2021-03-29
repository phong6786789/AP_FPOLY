package com.subi.apsubi.screen.schedule

import com.subi.apsubi.HomeActivity
import com.subi.apsubi.data.base.viewmodel.BaseViewModel
import com.subi.apsubi.data.model.LichHoc

class ScheduleViewModel:BaseViewModel() {
    var list: List<LichHoc> = HomeActivity.listLH
}