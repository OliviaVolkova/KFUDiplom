package com.example.kfu_app.presentation.authorization.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.R
import com.example.kfu_app.databinding.FragmentSignInBinding
import com.example.kfu_app.presentation.fragments.main.MainFragment
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var userViewModel: SignInViewModel

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initLiveDataListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnSignIn.setOnClickListener {
                val user = userViewModel.signIn(
                    etEmail.text.toString(),
                    etPassword.text.toString()
                )
            }
            btnBackToMenu.setOnClickListener {
                findNavController().navigate(R.id.fragmentMenu)
                requireContext().clickSound()
            }
        }
    }

    private fun initLiveDataListeners() {
        lifecycleScope.launch {
            userViewModel.observeSignInError().collect() {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}