package com.example.kfu_app.presentation.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.R
import com.example.kfu_app.data.repositories.FirebaseAuthRepository
import com.example.kfu_app.databinding.FragmentProfileBinding
import com.example.kfu_app.domain.model.UserInfo
import com.example.kfu_app.presentation.common.AlertDialogFragment
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    @Inject
    lateinit var firebaseAuthRepository: FirebaseAuthRepository

    @Inject
    lateinit var userViewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        initializeRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }

        lifecycleScope.launch {

            firebaseAuthRepository.currrentUserFlow().collect() {
                it?.let {
                    with(binding) {
                        tvName.text = it.name.let { llInfo ->
                            "${it.surName} ${it.name}\n${it.lastName}"
                        } ?: " "
                        tvBirthdayText2.text = it.dateOfBirth
                        tvCitizenshipText2.text = it.citizenship
                        tvGroupnumberText2.text = it.group
                        tvInstituteText2.text = it.institute
                        tvLibrarycardnumberText2.text = it.libraryCard
                        tvPersonalidText2.text = it.personalId.toString()
                        tvPlaceofbirthText2.text = it.placeOfBirth.toString()
                        Glide
                            .with(requireContext())
                            .load(it.image)
                            .circleCrop()
                            .fallback(R.drawable.kpfu_icon)
                            .into(ivProfileIcon)
                    }
                } ?: run {
                    findNavController().popBackStack().also {
                        requireContext().clickSound()
                    }
                }
            }
        }
    }

    private fun initializeRecycler() {
//        binding.rvSprints.let { rv ->
//            adapter = HistoryAdapter().also {
//                rv.adapter = it
//            }
//            rv.addItemDecoration(
//                DividerItemDecoration(
//                    requireContext(),
//                    DividerItemDecoration.VERTICAL
//                )
//            )
//        }
    }

    override fun onStart() {
        super.onStart()
        initListeners()
        initLiveDataListeners()
    }

    private fun initListeners() {
        with(binding) {
            btnSignOut.setOnClickListener {
                requireContext().clickSound()
                val dialog = AlertDialogFragment(action = {
                    userViewModel.signOut()
                }, null)
                dialog.show(parentFragmentManager, "Dialog")
            }
        }
    }

    private fun initLiveDataListeners() {
        userViewModel.getCurrentUser().observe(viewLifecycleOwner) { user ->
            user?.let {
                bindUser(user)
            }
        }

        lifecycleScope.launch() {
            userViewModel.getError().collect() {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                Log.d(
                    "MYTAG",
                    "HistoryFragment: initLiveDataListeners: HISTORY ERROR ${it.message}"
                )
            }
        }

    }

    private fun bindUser(user: UserInfo) {
        with(binding) {
            Log.d("MYTAG", "email ${user.email} name ${user.name}")
//            tvNames.text = "${user.name} ${user.lastName} ${user.surName}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}