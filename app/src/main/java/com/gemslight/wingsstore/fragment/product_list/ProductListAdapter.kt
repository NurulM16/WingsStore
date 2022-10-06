package com.gemslight.wingsstore.fragment.product_list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gemslight.common.entity.ProductEntity
import com.gemslight.wingsstore.databinding.LayoutProductListItemBinding

class ProductListAdapter(val productListToDetail: (ProductEntity) -> Unit) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(val binding: LayoutProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductEntity) {
            binding.textViewProductName.text = data.productName

            if (data.discount.toString().equals("0.0")) {
                binding.textViewProductDiscount.visibility = View.GONE
                binding.textViewProductPrice.text = "Rp. ${data.price}"
            } else {
                binding.textViewProductDiscount.text = data.price.toString()
                binding.textViewProductDiscount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                binding.textViewProductPrice.text =
                    "Rp. ${data.price - (data.price * data.discount / 100)}"
            }
            binding.btnBuy.setOnClickListener {
                productListToDetail(data)
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
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}