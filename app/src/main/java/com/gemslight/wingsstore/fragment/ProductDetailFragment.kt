package com.gemslight.wingsstore.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gemslight.common.base.BaseFragment
import com.gemslight.wingsstore.R
import com.gemslight.wingsstore.databinding.LayoutProductDetailBinding
import com.gemslight.wingsstore.view_model.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<ProductDetailViewModel, LayoutProductDetailBinding>() {
    override val vm: ProductDetailViewModel by viewModels()
    override val layoutResourceid: Int = R.layout.layout_product_detail
    private val navArgs: ProductDetailFragmentArgs by navArgs()

    override fun initBinding(binding: LayoutProductDetailBinding) = with(binding) {
        super.initBinding(binding)
        productName.text = navArgs.product.productName
        productDimension.text = navArgs.product.dimension
    }
}