package com.nkapps.lovelocalsample.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nkapps.lovelocalsample.R
import com.nkapps.lovelocalsample.data.util.Resource
import com.nkapps.lovelocalsample.databinding.FragmentHomeBinding
import com.nkapps.lovelocalsample.presentation.adapter.HomeAdapter
import com.nkapps.lovelocalsample.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel : HomeViewModel

    @Inject
    lateinit var adapter : HomeAdapter
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        viewModel.getAllProducts()

        viewModel.products.observe(viewLifecycleOwner){response ->
            when(response){
                is Resource.Success -> {
                    adapter.differ.submitList(response.data)
                    binding.homeRecyclerView.visibility = View.VISIBLE
                    Log.i("HomeFragment","${response.data}")
                }
                is Resource.Loading -> {
                    //binding.homeRecyclerView.visibility = View.INVISIBLE
                    Log.i("HomeFragment","Fetching data...")
                }
                is Resource.Error -> {
                    Log.i("HomeFragment","${response.message}")
                }
            }
        }

        adapter.setOnItemClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.homeRecyclerView.adapter = adapter

        binding.homeSearchView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

    }



}