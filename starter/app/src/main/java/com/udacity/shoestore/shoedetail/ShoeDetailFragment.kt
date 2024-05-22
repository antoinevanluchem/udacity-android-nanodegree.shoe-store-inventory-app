package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_shoe_detail, container, false)

        val newShoe = Shoe("550", 43.0, "New Balance", "My favourite shoes",
            R.drawable.new_balance_550_white
        )
        val viewModelFactory = ShoeDetailViewModelFactory(newShoe)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)

        binding.editCompanyName.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_GO ||
                actionId == EditorInfo.IME_ACTION_NEXT) {
                viewModel.setCompanyName(binding.editCompanyName.text.toString())
                true
            } else {
                false
            }
        }


        binding.save.setOnClickListener { it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(viewModel.shoe.value)) }
        binding.cancel.setOnClickListener { it.findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(null)) }

        return binding.root
    }
}