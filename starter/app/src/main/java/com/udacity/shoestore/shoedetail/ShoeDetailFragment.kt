package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail, container, false
        )
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        binding.save.setOnClickListener {
            saveShoe()
            it.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
        binding.cancel.setOnClickListener {
            viewModel.onCancelEditShoe()
            it.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.shoeImage.setOnClickListener {
            viewModel.switchImage()
        }

        binding.editShoeSize.addOnChangeListener { _, value, _ ->
            viewModel.setShoeSize(value.toDouble())
        }

        viewModel.detailedShoe.observe(viewLifecycleOwner, Observer {
            displayShoe(it)
        })

        return binding.root
    }

    private fun saveShoe() {
        viewModel.setCompanyName(binding.editCompanyName.text.toString())
        viewModel.setName(binding.editName.text.toString())

        viewModel.onShoeSaved()
    }

    private fun displayShoe(shoe: Shoe) {
        binding.editCompanyName.setText(shoe.company)
        binding.editName.setText(shoe.name)
        binding.editShoeSize.setValue(shoe.size.toFloat())
        binding.shoeImage.setImageResource(shoe.image)
    }
}

