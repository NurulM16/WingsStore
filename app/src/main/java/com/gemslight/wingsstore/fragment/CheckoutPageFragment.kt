package com.gemslight.wingsstore.fragment

import androidx.fragment.app.viewModels
import com.gemslight.common.base.BaseFragment
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutCheckoutPageBinding
import com.gemslight.wingsstore.view_model.CheckoutPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutPageFragment: BaseFragment<CheckoutPageViewModel, LayoutCheckoutPageBinding>() {
    override val vm: CheckoutPageViewModel by viewModels()

    override val layoutResourceid: Int = R.layout.layout_checkout_page
}