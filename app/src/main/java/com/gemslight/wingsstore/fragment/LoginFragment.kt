package com.gemslight.wingsstore.fragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gemslight.common.base.BaseFragment
import com.gemslight.common.base.ResponseLoading
import com.gemslight.common.base.ResponseSuccess
import com.gemslight.common.entity.LoginEntity
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutLoginBinding
import com.gemslight.wingsstore.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, LayoutLoginBinding>() {
    override val vm: LoginViewModel by viewModels()
    override val layoutResourceid: Int = R.layout.layout_login

    override fun initBinding(binding: LayoutLoginBinding) = with(binding) {
        super.initBinding(binding)
        btnLogin.setOnClickListener {
            val inputUsername = editTextUsername.text
            val inputPassword = editTextPassword.text
            if (inputUsername.isNullOrEmpty() || inputPassword.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "username and password can not blank",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.loginUser(
                    loginEntity = LoginEntity(
                        inputUsername.toString(),
                        inputPassword.toString()
                    )
                )
            }
        }
        observeLiveData()
        toRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.loginToRegister())
        }
    }

    fun observeLiveData() {
        vm.loginDataState?.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseSuccess -> {
                    Toast.makeText(this.context, "login Success", Toast.LENGTH_LONG).show()
                    findNavController().navigate(LoginFragmentDirections.loginToProductList())
                }
                is ResponseLoading -> {
                    Toast.makeText(this.context, "Please wait...", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this.context, "login Failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}