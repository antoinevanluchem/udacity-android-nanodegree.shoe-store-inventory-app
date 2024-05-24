package com.udacity.shoestore.shoe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {

    //
    // Variables
    //
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeViewModel

    //
    // onAction
    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            viewModel.onShoeSaved()
        }

        binding.cancel.setOnClickListener {
            navigateToShoeList()
            viewModel.onCancelEditShoe()
        }
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

        binding.editCompanyName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { viewModel.setCompanyName(it.toString()) }
            }
        })

        binding.editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { viewModel.setName(it.toString()) }
            }
        })

        binding.editDescription.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { viewModel.setDescription(it.toString()) }
            }
        })
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

