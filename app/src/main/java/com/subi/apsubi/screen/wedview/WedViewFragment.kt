package com.subi.apsubi.screen.wedview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.subi.apsubi.R

class WedViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_wed_view, container, false)
       val url = arguments?.getString("url").toString()
        val tvWed:TextView = view.findViewById(R.id.tvWed)
        tvWed.text = url
        return view
    }
}