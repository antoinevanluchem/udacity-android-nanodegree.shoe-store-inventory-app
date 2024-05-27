package com.udacity.shoestore

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        setUpButtons()

        return binding.root
    }

    //
    // Set up login and sign up button
    //
    private fun setUpButtons() {
        // Clicking either button should navigate to the Welcome Screen
        binding.loginButton.setOnClickListener(::login)
        binding.signUpButton.setOnClickListener(::signUp)
    }

    /**
     * Check if password and email match in database
     */
    private fun login(v: View) {
        if (isEmailValid() && isPasswordValid()) {
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        } else {
            Toast.makeText(requireContext(), "Unable to login.", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Do some verifications and add user to database
     */
    private fun signUp(v: View) {
        if (isEmailValid() && isPasswordValid()) {
            v.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        } else {
            Toast.makeText(requireContext(), "Unable to sign up.", Toast.LENGTH_SHORT).show()
        }
    }

    //
    // Email an dpassword validation
    //
    /**
     * For now, simple email validation
     */
    private fun isEmailValid(): Boolean {
//        val email = binding.emailEditText.text.toString().trim()
//        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return true
    }

    /**
     * For now, password needs to be not empty
     */
    private fun isPasswordValid(): Boolean {
//        val text = binding.passwordEditText.text.toString().trim()
//        return text.isNotEmpty()
        return true
    }
}