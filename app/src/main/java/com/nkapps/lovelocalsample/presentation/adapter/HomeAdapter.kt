package com.nkapps.lovelocalsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nkapps.lovelocalsample.data.model.ShopItem
import com.nkapps.lovelocalsample.databinding.SingleItemBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<ShopItem>() {
        override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)

    private var onItemClickListener : ((ShopItem)-> Unit) = {}
    private var onAddToCartClickListener : ((ShopItem)-> Unit) = {}

    fun setOnItemClickListener(listener : (ShopItem)-> Unit){
        onItemClickListener = listener
    }

    fun setOnAddToCartClickListener(listener : (ShopItem)-> Unit){
        onAddToCartClickListener = listener
    }

    inner class HomeViewHolder(private val binding : SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(shopItem: ShopItem){

//            Glide.with(binding.itemImage)
//                .load(shopItem.image)
//                .into(binding.itemImage)

            binding.itemTitle.text = shopItem.title
//            binding.itemPrice.text = "USD ${shopItem.price}"
//            binding.itemRating.text = "${shopItem.rating.rate}"
//            binding.itemReview.text = "${shopItem.rating.count} Reviews"

            binding.itemView.setOnClickListener {
                onItemClickListener(shopItem)
            }

            binding.btnAdd.setOnClickListener {
                onAddToCartClickListener(shopItem)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val shopItem = differ.currentList[position]
        holder.bindData(shopItem)
    }

    override fun getItemCount() =  differ.currentList.size



}