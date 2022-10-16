package com.gemslight.wingsstore.fragment.product_list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gemslight.common.base.BaseFragment
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutProductListBinding
import com.gemslight.wingsstore.view_model.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductListFragment : BaseFragment<ProductListViewModel, LayoutProductListBinding>() {
    override val vm: ProductListViewModel by viewModels()
    override val layoutResourceid: Int = R.layout.layout_product_list
    private val adapter = ProductListAdapter {
        findNavController().navigate(ProductListFragmentDirections.productListToProductDetail(it))
    }

    override fun initBinding(binding: LayoutProductListBinding) = with(binding) {
        super.initBinding(binding)
        recyclerViewProductList.adapter = adapter
        observeLiveData()
        callback()
        btnCheckout.setOnClickListener {
            findNavController().navigate(ProductListFragmentDirections.productListToCheckout())
        }


    }

    fun observeLiveData() {
        vm.productListDataState?.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }
    }
}