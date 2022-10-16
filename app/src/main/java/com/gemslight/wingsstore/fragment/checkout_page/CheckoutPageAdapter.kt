package com.gemslight.wingsstore.fragment.checkout_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gemslight.common.entity.relational.ProductCartCheckout
import com.gemslight.common.entity.ProductEntity
import com.gemslight.wingsstore.databinding.LayoutCheckoutPageItemBinding

class CheckoutPageAdapter(val setTotal: () -> Unit) :
    RecyclerView.Adapter<CheckoutPageAdapter.CheckoutPageViewHolder>() {

    val total = mutableMapOf<ProductEntity, Double>()

    inner class CheckoutPageViewHolder(val binding: LayoutCheckoutPageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productCartCheckout: ProductCartCheckout) {
            binding.textViewProductName.text = productCartCheckout.productEntity.productName
            binding.textViewProductUnit.text = productCartCheckout.productEntity.unit
            Glide.with(binding.imageViewProductImage).load(productCartCheckout.productEntity.image)
                .into(binding.imageViewProductImage)


            binding.editTextProductQuantity.setText("1")
            binding.imageViewMinusProduct.isEnabled = false
            setTotalPrice(
                productCartCheckout.productEntity,
                productCartCheckout.productEntity.price * binding.editTextProductQuantity.text.toString()
                    .toInt()
            )

            binding.editTextProductQuantity.addTextChangedListener {
                var subTotalPrice = if (!it.isNullOrEmpty())
                    (Integer.valueOf(it.toString()) * (productCartCheckout.productEntity.price -
                            (productCartCheckout.productEntity.price * productCartCheckout.productEntity.discount / 100)))
                else
                    0.0
                if (it.isNullOrEmpty() || it.equals("0")) {
                    binding.editTextProductQuantity.setText("1")
                }
                binding.imageViewMinusProduct.isEnabled =
                    productCartCheckout.productCartEntity.quantity > 1
                binding.textViewSubTotalPrice.text = "Rp. $subTotalPrice"

                total[productCartCheckout.productEntity] = subTotalPrice

                setTotal()
            }
            binding.editTextProductQuantity.setText(productCartCheckout.productCartEntity.quantity.toString())
            binding.imageViewAddProduct.setOnClickListener {
                productCartCheckout.productCartEntity.quantity += 1
                binding.editTextProductQuantity.setText(productCartCheckout.productCartEntity.quantity.toString())
            }

            binding.imageViewMinusProduct.setOnClickListener {
                productCartCheckout.productCartEntity.quantity -= 1
                binding.editTextProductQuantity.setText(productCartCheckout.productCartEntity.quantity.toString())
            }
        }
    }

    fun setTotalPrice(productEntity: ProductEntity, subtotalPrice: Double) {
        total[productEntity] = subtotalPrice


    }


    val differ = AsyncListDiffer(this, itemCallback)

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ProductCartCheckout>() {
            override fun areItemsTheSame(
                oldItem: ProductCartCheckout,
                newItem: ProductCartCheckout
            ): Boolean = oldItem.productEntity.productCode == newItem.productEntity.productCode

            override fun areContentsTheSame(
                oldItem: ProductCartCheckout,
                newItem: ProductCartCheckout
            ): Boolean = false

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutPageViewHolder =
        CheckoutPageViewHolder(
            LayoutCheckoutPageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CheckoutPageViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

}
