package com.subi.apsubi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LichHoc(
    @PrimaryKey val stt: String,
    val ngay: String,
    val phong: String,
    val diaChi: String,
    val maMon: String,
    val mon: String,
    val lop: String,
    val giangVien: String,
    val Ca: String,
    val thoiGian: String,
    val chitiet: String
)
