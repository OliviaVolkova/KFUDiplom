package com.example.kfu_app.presentation.fragments.navigationDrawer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kfu_app.databinding.FragmentContactsBinding
import com.example.kfu_app.utils.clickSound

class ContactsFragment: Fragment(){

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ivVk.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/priemkpfu"))
                startActivity(intent).also {
                    requireContext().clickSound()
                }
            }
            ivTg.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/priemkpfu"))
                startActivity(intent).also {
                    requireContext().clickSound()
                }
            }
            ivTgChat.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/priemkpfusng"))
                startActivity(intent).also {
                    requireContext().clickSound()
                }
            }
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }

    }


}