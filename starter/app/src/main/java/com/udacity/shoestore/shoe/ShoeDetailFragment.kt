package com.udacity.shoestore.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding


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

        setUpButtons()
        setUpShoeEdit()
        setUpObservers()

        return binding.root
    }

    //
    // Set up save and cancel buttons
    //
    private fun setUpButtons() {
        binding.save.setOnClickListener {
            navigateToShoeList()
            saveEditTextFields()
            viewModel.onShoeSaved()
        }

        binding.cancel.setOnClickListener {
            navigateToShoeList()
            viewModel.onCancelEditShoe()
        }
    }

    private fun saveEditTextFields() {
        viewModel.setCompanyName(binding.editCompanyName.text.toString())
        viewModel.setName(binding.editName.text.toString())
        viewModel.setDescription(binding.editDescription.text.toString())
    }

    private fun navigateToShoeList() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

    //
    // Set up fields to edit shoe
    //

    private fun setUpShoeEdit() {
        binding.shoeImage.setOnClickListener {
            viewModel.switchImage()
        }

        binding.editShoeSize.addOnChangeListener { _, value, _ ->
            viewModel.setShoeSize(value.toDouble())
        }
    }

    //
    // Observe viewModel.detailedShoe
    //
    private fun setUpObservers() {
        viewModel.detailedShoe.observe(viewLifecycleOwner, Observer {
            displayShoe(it)
        })
    }

    private fun displayShoe(shoe: Shoe) {
        binding.editCompanyName.setText(shoe.company)
        binding.editName.setText(shoe.name)
        binding.editDescription.setText(shoe.description)
        binding.editShoeSize.value = shoe.size.toFloat()
        binding.shoeImage.setImageResource(shoe.image)
    }
}

