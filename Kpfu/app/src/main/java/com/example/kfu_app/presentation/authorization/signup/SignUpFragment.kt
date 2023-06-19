package com.example.kfu_app.presentation.authorization.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.databinding.FragmentSignUpBinding
import com.example.kfu_app.presentation.di.VMProviderFactory
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpFragment : Fragment() {

    @Inject
    lateinit var viewModelProviderFactory: VMProviderFactory
    lateinit var userViewModel: SignUpViewModel

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        ApplicationDelegate.getScreenComponent().inject(this)
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(
            viewModelStore,
            viewModelProviderFactory
        ).get(SignUpViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initLiveDataListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnSignUp.setOnClickListener {
                userViewModel.signUp(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                ).also {
                    requireContext().clickSound()
                }
            }
            btnSignIn.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
                requireContext().clickSound()
            }
        }
    }

    private fun initLiveDataListeners() {
        lifecycleScope.launch {
            userViewModel.getSignUpErrorFlow().collect() {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}