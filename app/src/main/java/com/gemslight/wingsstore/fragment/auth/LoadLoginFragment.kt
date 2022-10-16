package com.gemslight.wingsstore.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gemslight.wingsstore.databinding.LayoutLoadLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoadLoginFragment : Fragment() {

    private var bindingLoad: LayoutLoadLoginBinding? = null
    val binding get() = bindingLoad


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingLoad = LayoutLoadLoginBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().popBackStack()
    }
}