package com.udacity.shoestore.shoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeElementBinding


class ShoeListFragment : Fragment() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutInflater = inflater
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            displayShoeList(it)
        })

        binding.addShoeButton.setOnClickListener {
            viewModel.onAddShoe()
            it.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }


        return binding.root
    }

    private fun displayShoeList(shoeList: MutableList<Shoe>) {
        binding.shoeList.removeAllViews()
        shoeList.forEachIndexed { index, shoe ->
            val inflatedShoeBinding: ShoeElementBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.shoe_element,
                binding.shoeList,
                false
            )

            inflatedShoeBinding.companyAndName.text =
                getString(R.string.company_and_name_format, shoe.company, shoe.name)
            inflatedShoeBinding.size.text = getString(R.string.shoe_size_format,shoe.size)
            inflatedShoeBinding.description.text = shoe.description
            inflatedShoeBinding.shoeImage.setImageResource(shoe.image)

            inflatedShoeBinding.shoeImage.setOnClickListener {
                viewModel.onShoeSelected(index)
                it.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
            }

            binding.shoeList.addView(inflatedShoeBinding.root)
        }
    }
}