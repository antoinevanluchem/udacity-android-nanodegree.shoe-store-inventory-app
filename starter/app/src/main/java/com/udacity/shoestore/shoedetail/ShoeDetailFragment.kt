package com.udacity.shoestore.shoedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        displayShoe(viewModel.detailedShoe)

        binding.save.setOnClickListener {
            saveShoe()
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        binding.cancel.setOnClickListener {
            viewModel.onEditShoeCancelled()
            it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        return binding.root
    }
    
    private fun saveShoe() {
        viewModel.setCompanyName(binding.editCompanyName.text.toString())
        viewModel.onShoeSaved()
    }

    private fun displayShoe(shoe: Shoe) {
        binding.editCompanyName.setText(shoe.company)
    }
}

