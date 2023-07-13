package com.nkapps.lovelocalsample.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nkapps.lovelocalsample.R
import com.nkapps.lovelocalsample.data.model.ShopItem
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.databinding.FragmentSearchBinding
import com.nkapps.lovelocalsample.presentation.adapter.SearchAdapter
import com.nkapps.lovelocalsample.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModel : HomeViewModel

    @Inject
    lateinit var adapter : SearchAdapter

    private lateinit var binding : FragmentSearchBinding

    private var productsList = listOf<ShopItem>()
    private var productsList2 = listOf<ShopItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding = FragmentSearchBinding.bind(view)

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){result ->
            when(result){
                is Resource.Success -> {
                    productsList = result.data!!
                    adapter.differ.submitList(result.data)
                }
                is Resource.Loading -> {
                    Log.i("SearchFragment","fetching..")
                }
                is Resource.Error -> {
                    result.message?.let { Log.i("SearchFragment", it) }
                }
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$query",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsList2 = productsList.filter { it.title.contains("$newText",ignoreCase = true)}
                adapter.differ.submitList(productsList2)
                return true
            }

        })

        binding.searchView.setOnCloseListener {
            adapter.differ.submitList(productsList)
            true
        }

        binding.searchRecyclerView.adapter = adapter

        adapter.setOnItemClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
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

}