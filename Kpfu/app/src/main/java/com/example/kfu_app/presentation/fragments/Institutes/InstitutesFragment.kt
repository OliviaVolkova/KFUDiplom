package com.example.kfu_app.presentation.fragments.Institutes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.R
import com.example.kfu_app.data.repositories.FirebaseInstitutesRepository
import com.example.kfu_app.databinding.FragmentInstitutesBinding
import com.example.kfu_app.presentation.common.NavigationDrawerManager
import com.example.kfu_app.presentation.fragments.webView.WebViewFragment
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import javax.inject.Inject


class InstitutesFragment: Fragment() {

    private var _binding: FragmentInstitutesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var firebaseInstitutesRepository: FirebaseInstitutesRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstitutesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)
        binding.ivBack
            .setOnClickListener {
            (requireActivity() as NavigationDrawerManager).showDrawer().also {
                requireContext().clickSound()
            }
        }

        val instituteslayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val institutesList = binding.rvInstitutes
        institutesList.layoutManager = instituteslayoutManager

        val listener: (String) -> Unit = { link ->
            val args = Bundle()
            args.putString(WebViewFragment.ARG_URL, link)
            findNavController().navigate(R.id.webViewFragment, args)
        }

        val institutesAdapter = InstitutesAdapter(requireContext(), listener).also {
            institutesList.adapter = it
        }

        lifecycleScope.launch {
            val institutes = firebaseInstitutesRepository.getInstitutes()
            Log.d("INSTITUTES", "RESULT $institutes ")
            institutesAdapter.submitList(institutes)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }

    }
}