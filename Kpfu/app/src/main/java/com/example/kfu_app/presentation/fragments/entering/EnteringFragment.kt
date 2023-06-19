package com.example.kfu_app.presentation.fragments.entering

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.databinding.FragmentEnteringBinding
import com.example.kfu_app.domain.repositories.EnteringRepository
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EnteringFragment : Fragment() {

    private var _binding: FragmentEnteringBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var firebaseEnteringRepository: EnteringRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.component.getScreenComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnteringBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            val images = firebaseEnteringRepository.getImages()
            val viewsToAddImage = listOf(
                binding.img1, binding.img2
            )

            images.forEachIndexed { index, image ->
                withContext(Dispatchers.Main) {
                    Glide
                        .with(requireContext())
                        .load(image.imgUrl)
                        .into(viewsToAddImage[index])
                }
            }

            binding.btnOpen.setOnClickListener {
                try {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://admissions.kpfu.ru/priem-v-universitet/skhema-postupleniya-bakalavriat/specialitet")
                    )
                    requireContext().startActivity(intent).also {
                        requireContext().clickSound()
                    }
                } catch (e: Exception) {
                    throw NullPointerException("Cтраницы не существует")
                }
            }

            binding.ivBack.setOnClickListener {
                findNavController().popBackStack().also {
                    requireContext().clickSound()
                }
            }
        }
    }
}