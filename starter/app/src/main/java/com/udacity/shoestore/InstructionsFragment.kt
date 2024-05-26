package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionEmptyShoeListBinding

class InstructionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionEmptyShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction_empty_shoe_list, container, false)

        binding.button.setOnClickListener { it.findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment()) }

        return binding.root
    }
}