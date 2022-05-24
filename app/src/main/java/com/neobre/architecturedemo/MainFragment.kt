package com.neobre.architecturedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.vBtnMvc).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mvcFragment)
        }
        view.findViewById<Button>(R.id.vBtnMvp).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mvpFragment)
        }
        view.findViewById<Button>(R.id.vBtnMvvm).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mvvmFragment)
        }
        view.findViewById<Button>(R.id.vBtnMvi).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_mviFragment)
        }
    }
}
