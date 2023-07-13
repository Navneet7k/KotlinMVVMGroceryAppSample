package com.nkapps.lovelocalsample.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.nkapps.lovelocalsample.R
import com.nkapps.lovelocalsample.data.model.CartItem2
import com.nkapps.lovelocalsample.data.model.ShopItem
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.data.util.Utils
import com.nkapps.lovelocalsample.databinding.FragmentProductDetailBinding
import com.nkapps.lovelocalsample.presentation.viewmodel.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding : FragmentProductDetailBinding

    @Inject
    lateinit var viewModel : ProductDetailViewModel

    private lateinit var shopItem: ShopItem

    private var like = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopItem = ProductDetailFragmentArgs.fromBundle(requireArguments()).shopItem

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_product_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProductDetailBinding.bind(view)

        updateViews()

        binding.productDetailAddToCart.setOnClickListener {
            val cartItem = CartItem2(shopItem.id,shopItem.image,Utils.formatPrice(shopItem.price.toString()),shopItem.title,1,shopItem.price)
            viewModel.saveToCart(cartItem)
            Snackbar.make(binding.productDetailAddToCart,"Added to Cart Successfully",Snackbar.LENGTH_SHORT).show()
        }

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

    private fun updateViews() {

        //data from remote api ignored and added dummy data from figma
        binding.productDetailPrice.text = "5% OFF"
        binding.productDetailTitle.text = "Tata Tea Gold (250gm) + Nestle Every Dairy Powder (100gm)"
        binding.productDetailDescription.text = "The speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. \\n\\nThe speaker unit contains a diaphragm that is precision-grown from NAC Audio bio-cellulose, making it stiffer, lighter and stronger than regular PET speaker units, and allowing the sound-producing diaphragm to vibrate without the levels of distortion found in other speakers. "

//        Glide.with(binding.productDetailImage)
//            .load(shopItem.image)
//            .into(binding.productDetailImage)

//        binding.productDetailRating.text = "${shopItem.rating.rate}"
//        binding.productDetailReviews.text = "${shopItem.rating.count} Reviews"

    }

}