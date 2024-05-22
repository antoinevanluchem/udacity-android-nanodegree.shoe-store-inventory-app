package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoelist.ShoeListFragmentArgs

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoe_detail, container, false)

        val viewModelFactory = ShoeDetailViewModelFactory(getDisplayedShoe())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)

        viewModel.shoe.observe(this, Observer {
            displayShoe(it)
        })

        binding.save.setOnClickListener {
            saveShoe()
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(viewModel.shoe.value))
        }
        binding.cancel.setOnClickListener { it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(null)) }

        return binding.root
    }

    private fun getDisplayedShoe(): Shoe {
        val shoeListFragmentArgs by navArgs<ShoeDetailFragmentArgs>()
        return shoeListFragmentArgs.shoe ?: Shoe("Name", 0.0, "Company", "", R.drawable.no_shoe_image_available)
    }
    
    private fun saveShoe() {
        viewModel.setCompanyName(binding.editCompanyName.text.toString())
    }

    private fun displayShoe(shoe: Shoe) {
        binding.editCompanyName.setText(shoe.company)
    }
}