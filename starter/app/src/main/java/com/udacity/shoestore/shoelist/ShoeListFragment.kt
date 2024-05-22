package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeElementBinding
import com.udacity.shoestore.models.Shoe


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
            Log.i("ShoeListFragment", it.toString())
            displayShoeList(it)
        })

        binding.addShoeButton.setOnClickListener {
            it.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }


        return binding.root
    }

    private fun displayShoeList(shoeList: MutableList<Shoe>) {
        binding.shoeList.removeAllViews()
        shoeList.forEachIndexed { index, shoe ->
            Log.i("ShoeListFragment", "in display shoe list")
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
                viewModel.setIndexOfDetailedShoe(index)
                it.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
            }

            binding.shoeList.addView(inflatedShoeBinding.root)
        }
    }
}