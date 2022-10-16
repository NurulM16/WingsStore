package com.gemslight.wingsstore.fragment.product_detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

    override fun initBinding(binding: LayoutProductDetailBinding) =
        with(binding) {
            super.initBinding(binding)
            Glide.with(requireContext()).load(navArgs.product.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.productImage)
            productName.text = navArgs.product.productName
            productDimension.text = navArgs.product.dimension
            val productDiscount =
                ((navArgs.product.price * navArgs.product.discount / 100))
            productPrice.text =
                "${navArgs.product.currency} ${navArgs.product.price - productDiscount}"
            productUnit.text = navArgs.product.unit

            btnBuy.setOnClickListener {
                vm.insertProductToCart(navArgs.product.productCode)
                Toast.makeText(
                    requireContext(),
                    "Product added successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}
