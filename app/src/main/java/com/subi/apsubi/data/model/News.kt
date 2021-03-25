package com.subi.apsubi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey val title: String,
    val link: String,
    val derector: String,
    val time: String
)