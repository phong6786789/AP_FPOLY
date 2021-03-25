package com.subi.apsubi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiemDanh(
    @PrimaryKey val baihoc: String,
    val ngay: String,
    val ca: String,
    val giangVien: String,
    val moTa: String,
    val trangThai: String,
    val ghichu: String
)