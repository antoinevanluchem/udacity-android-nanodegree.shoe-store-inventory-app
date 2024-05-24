package com.udacity.shoestore.shoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeElementBinding


class ShoeListFragment : Fragment() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewModel: ShoeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOverflowMenu()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false);
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(false);
    }

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

    private fun setUpOverflowMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return if (menuItem.itemId == R.id.logout) {
                    findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())

                    viewModel.resetShoeList()
                    true
                } else {
                    false
                }
            }
        }, viewLifecycleOwner)
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