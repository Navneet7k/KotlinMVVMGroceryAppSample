package com.nkapps.lovelocalsample.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nkapps.lovelocalsample.R
import com.nkapps.lovelocalsample.data.util.Utils
import com.nkapps.lovelocalsample.databinding.FragmentCartBinding
import com.nkapps.lovelocalsample.presentation.adapter.CartAdapter
import com.nkapps.lovelocalsample.presentation.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment(){

    private lateinit var binding : FragmentCartBinding
    @Inject
    lateinit var cartViewModel: CartViewModel
    @Inject
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_cart,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        cartViewModel.getCartItems().observe(viewLifecycleOwner){
            cartAdapter.differ.submitList(it)
        }

        cartAdapter.setOnRemoveClickListener {
            cartViewModel.deleteCart(it)
        }

        cartAdapter.incrementClickListener {
            cartViewModel.increment(it)
        }

        cartAdapter.decrementClickListener {
            cartViewModel.decrement(it)
        }

        cartViewModel.totalItems.observe(viewLifecycleOwner){
            binding.cartItemsInfo.text = "Total $it Items"
        }

        cartViewModel.totalItemsPrice.observe(viewLifecycleOwner){
            binding.cartItemsPrice.text = "Rs ${Utils.formatPrice(it.toString())}"
        }

        binding.cartRecyclerView.adapter = cartAdapter

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp() // Handle back button press
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}