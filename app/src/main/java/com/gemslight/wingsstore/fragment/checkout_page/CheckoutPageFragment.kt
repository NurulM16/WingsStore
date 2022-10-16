package com.gemslight.wingsstore.fragment.checkout_page

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gemslight.common.base.BaseFragment
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutCheckoutPageBinding
import com.gemslight.wingsstore.view_model.CheckoutPageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CheckoutPageFragment : BaseFragment<CheckoutPageViewModel, LayoutCheckoutPageBinding>() {
    override val vm: CheckoutPageViewModel by viewModels()
    override val layoutResourceid: Int = R.layout.layout_checkout_page
    private val adapter = CheckoutPageAdapter {
        setTotalPrice()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun initBinding(binding: LayoutCheckoutPageBinding) {
        super.initBinding(binding)
        binding.recyclerViewCheckout.adapter = adapter
        observeLiveData()
        binding.btnConfirm.setOnClickListener {
            if (vm.subTotalPriceProduct.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Please add product",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                vm.insertProductCheckoutToTransactionDetail()
                vm.deleteAllCart()
                vm.subTotalPriceProduct.clear()
                Toast.makeText(requireContext(), "Transaction Successfully", Toast.LENGTH_SHORT)
                    .show()
                findNavController().popBackStack()
            }

        }
    }

    fun observeLiveData() {
        vm.checkoutDataState?.observe(viewLifecycleOwner) { it ->
            adapter.differ.submitList(it)
            it.forEach {
                vm.subTotalPriceProduct[it] =
                    it.productEntity.price - (it.productEntity.price * it.productEntity.discount / 100)
            }
            setTotalPrice()
        }
    }

    fun setTotalPrice() {
        var total = 0.0

        adapter.total.forEach {
            total += it.value
        }
        binding.textViewTotalPrice.text = "Rp. ${total}"
    }
}