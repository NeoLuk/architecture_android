package com.neobre.architecturedemo.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.neobre.architecturedemo.R
import com.neobre.architecturedemo.databinding.FragmentMvvmBinding
import com.neobre.architecturedemo.util.ViewModelFactory

class MvvmFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel: MvvmViewModel by viewModels { ViewModelFactory(MvvmRepository(MvvmDataSource())) }

        return DataBindingUtil.inflate<FragmentMvvmBinding?>(
            inflater,
            R.layout.fragment_mvvm,
            container,
            false
        ).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root
    }
}
