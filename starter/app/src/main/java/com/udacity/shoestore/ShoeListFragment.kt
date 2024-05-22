package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeElementBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var layoutInflater: LayoutInflater
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutInflater = inflater
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        initShoeList(mutableListOf(Shoe("550", 43.0, "New Balance", "My favourite shoes")))

        return binding.root
    }

    private fun initShoeList(shoeList: MutableList<Shoe>) {
        for (shoe in shoeList) {
            val inflatedShoeBinding: ShoeElementBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.shoe_element,
                binding.shoeList,
                false
            )

            inflatedShoeBinding.companyAndName.text = "${shoe.company} ${shoe.name}"
            inflatedShoeBinding.size.text = shoe.size.toString()
            inflatedShoeBinding.description.text = shoe.description
            inflatedShoeBinding.shoeImage.setImageResource(R.drawable.new_balance_550_white)

            binding.shoeList.addView(inflatedShoeBinding.root)
        }
    }
}