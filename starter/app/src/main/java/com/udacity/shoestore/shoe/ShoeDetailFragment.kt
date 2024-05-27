package com.udacity.shoestore.shoe

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.addAfterTextChangedListener
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

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setUpButtons()
        setUpShoeEdit()

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
            viewModel.setShoeSize(value)
        }

        binding.editCompanyName.addAfterTextChangedListener { viewModel.setCompanyName(it) }
        binding.editName.addAfterTextChangedListener { viewModel.setName(it) }
        binding.editDescription.addAfterTextChangedListener { viewModel.setDescription(it) }

    }
}

