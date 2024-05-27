package com.udacity.shoestore.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionShoeDetailsBinding
import com.udacity.shoestore.databinding.FragmentInstructionShoeListBinding

class InstructionFragmentShoeList : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionShoeListBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_instruction_shoe_list, container, false)

        binding.button.setOnClickListener { it.findNavController().navigate(InstructionFragmentShoeListDirections.actionInstructionFragmentShoeListToShoeListFragment()) }

        return binding.root
    }
}