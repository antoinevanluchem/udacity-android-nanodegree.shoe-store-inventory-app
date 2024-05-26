package com.udacity.shoestore.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionShoeDetailsBinding

class InstructionFragmentShoeDetails : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionShoeDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_instruction_shoe_details, container, false)

        binding.button.setOnClickListener { it.findNavController().navigate(InstructionFragmentShoeDetailsDirections.actionInstructionsFragmentShoeDetailsToInstructionFragmentShoeList()) }

        return binding.root
    }
}