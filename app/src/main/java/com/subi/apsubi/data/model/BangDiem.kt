package com.subi.apsubi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BangDiem(
    @PrimaryKey val stt: String,
    val ky: String,
    val hocKy: String,
    val mon: String,
    val maMon: String,
    val maMon2: String,
    val tinChi: String,
    val diem: String,
    val trangThai: String
)
