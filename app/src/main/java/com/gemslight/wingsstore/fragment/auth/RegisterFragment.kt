package com.gemslight.wingsstore.fragment.auth

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gemslight.common.base.BaseFragment
import com.gemslight.common.entity.LoginEntity
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutRegisterBinding
import com.gemslight.wingsstore.view_model.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterViewModel, LayoutRegisterBinding>() {
    override val vm: RegisterViewModel by viewModels()
    override val layoutResourceid: Int = R.layout.layout_register


    override fun initBinding(binding: LayoutRegisterBinding) = with(binding) {
        super.initBinding(binding)
        btnRegister.setOnClickListener {
            val inputUsername = editTextUsername.text
            val inputPassword = editTextPassword.text
            if (inputUsername.isNullOrEmpty() || inputPassword.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "username and password can't blank",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.registerUser(
                    LoginEntity(
                        inputUsername.toString(),
                        inputPassword.toString()
                    )
                )
                Toast.makeText(requireContext(), "register success", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
        toLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.registerToLogin())
        }
    }

}