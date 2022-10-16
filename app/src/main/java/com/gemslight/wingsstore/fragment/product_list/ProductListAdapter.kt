package com.gemslight.wingsstore.fragment.product_list

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.gemslight.common.entity.ProductEntity
import com.gemslight.wingsstore.databinding.LayoutProductListItemBinding

class ProductListAdapter(val productListToDetail: (ProductEntity) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(val binding: LayoutProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
        fun bind(context: Context, productEntity: ProductEntity) {
            Glide.with(context).load(productEntity.image?.let {
                context.getDrawable(it)
            })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageViewProductList)

            binding.textViewProductName.text = productEntity.productName
            if (productEntity.discount.toString() == "0.0") {
                binding.textViewProductDiscount.visibility = View.GONE
                binding.textViewProductPrice.text =
                    "${productEntity.currency} ${productEntity.price}"
            } else {
                binding.textViewProductDiscount.text = productEntity.price.toString()
                binding.textViewProductDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                val productPriceAfterDiscount =
                    "${productEntity.currency} ${productEntity.price - (productEntity.price * productEntity.discount / 100)}"
                binding.textViewProductPrice.text = productPriceAfterDiscount

            }
            binding.btnBuy.setOnClickListener {
                productListToDetail(productEntity)
            }

        }

    }

    val differ = AsyncListDiffer(this, itemCallback)

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ProductEntity>() {
            override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
                return oldItem.productCode == oldItem.productCode
            }

            override fun areContentsTheSame(
                oldItem: ProductEntity,
                newItem: ProductEntity
            ): Boolean {
                return false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder =
        ProductListViewHolder(
            LayoutProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(holder.itemView.context, differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}

fun ProductListFragment.callback() {
    val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.moveTaskToBack(true)
            activity?.finish()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
}