package com.subi.apsubi

import android.os.Bundle
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list = arrayOf(
            "FPT Polytechnic Hà Nội",
            "FPT Polytechnic Đà Nẵng",
            "FPT Polytechnic Hồ Chí Minh",
            "FPT Polytechnic Tây Nguyên",
            "FPT Polytechnic Cần Thơ",
            "FPT Polytechnic HiTech",
            "FPT PTCĐ Hà Nội",
            "FPT PTCĐ Hồ Chí Minh",
            "FPT PTCĐ Đà Nẵng",
            "FPT PTCĐ Cần Thơ",
            "FPT PTCĐ Tây Nguyên"
        )


        val x = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, list
        )
        x.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spList.adapter = x

        btnGoogle.setOnClickListener {
            var posision = spList.selectedItemPosition
            val any = when (posision) {
                0 -> "ph"
                1 -> "pd"
                2 -> "ps"
                3 -> "pk"
                4 -> "pc"
                5 -> "ht"
                6 -> "th"
                7 -> "ts"
                8 -> "td"
                9 -> "tc"
                10 -> "tk"
                else -> Toast.makeText(this, "Vui lòng chọn cơ sở", Toast.LENGTH_SHORT).show()
            }


        }


    }


}