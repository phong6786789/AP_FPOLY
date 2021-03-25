package com.subi.apsubi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiemTheoKy(
    @PrimaryKey val stt: String,
    val tenBai: String,
    val trongSo: String,
    val diem: String,
    val ghiChu: String
)