package com.gemslight.wingsstore.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.view_model.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


@AndroidEntryPoint
class SplashFragment : Fragment(), CoroutineScope {
    val vm: LoginViewModel by viewModels()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                if (vm.isLoggedIn)
                    findNavController().navigate(SplashFragmentDirections.splashToProductList())
                else findNavController().navigate(SplashFragmentDirections.splashToLogin())
            }
        }
    }
}
