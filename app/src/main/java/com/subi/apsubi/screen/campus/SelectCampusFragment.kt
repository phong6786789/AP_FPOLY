package com.subi.apsubi.screen.campus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.subi.apsubi.R
import com.subi.apsubi.util.Constance

@Suppress("UNREACHABLE_CODE")
class SelectCampusFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_seclect_campus, container, false)
        //Spinner
        val sp: Spinner = view.findViewById(R.id.spList)
        val login: Button = view.findViewById(R.id.btnGoogle)
        val adapter = activity?.let {
            ArrayAdapter(
                it.applicationContext,
                android.R.layout.simple_list_item_checked, Constance.list
            )
        }
        sp.adapter = adapter
        sp.setSelection(2)

        login.setOnClickListener {
            val posision = sp.selectedItemPosition
            val any: String = when (posision) {
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
                else -> "erro"
            }

            if (any == "erro") {
                Toast.makeText(view.context, "Vui lòng chọn cơ sở", Toast.LENGTH_SHORT).show()
            } else {
                val url = "https://ap.poly.edu.vn/login/google?campus_id=${any}"
                val bundle = bundleOf("url" to url)
                findNavController().navigate(R.id.action_seclectCampusFragment_to_wedViewFragment, bundle)
            }
        }

        return view
    }


}