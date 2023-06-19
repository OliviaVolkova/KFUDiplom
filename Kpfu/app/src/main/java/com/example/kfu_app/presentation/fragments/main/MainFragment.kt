package com.example.kfu_app.presentation.fragments.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kfu_app.presentation.menu.MenuAdapter
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.R
import com.example.kfu_app.data.repositories.*
import com.example.kfu_app.databinding.FragmentMenuBinding
import com.example.kfu_app.domain.model.Feature
import com.example.kfu_app.domain.model.FeatureConfiguration
import com.example.kfu_app.domain.usecases.FeatureUseCase
import com.example.kfu_app.presentation.authorization.AuthActivity
import com.example.kfu_app.presentation.common.NavigationDrawerManager
import com.example.kfu_app.presentation.fragments.webView.WebViewFragment
import com.example.kfu_app.presentation.menu.ItemType
import com.example.kfu_app.presentation.menu.MenuItem
import com.example.kfu_app.presentation.news.NewsAdapter
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var firebaseNewsRepository: FirebaseNewsRepository

    @Inject
    lateinit var firebaseMenuRepository: FirebaseMenuRepository
    lateinit var newsAdapter: NewsAdapter
    lateinit var menuAdapter: MenuAdapter

    @Inject
    lateinit var firebaseAuthRepository: FirebaseAuthRepository

    @Inject
    lateinit var featureUseCase: FeatureUseCase

    private var menuList: List<MenuItem> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)

        observeConfiguration()
        binding.ivBurgerMenu.setOnClickListener {
            requireContext().clickSound()
            (requireActivity() as NavigationDrawerManager).showDrawer()
        }

        try {
            lifecycleScope.launch {
                firebaseAuthRepository.currrentUserFlow().collect() {
                    it?.let { info ->
                        binding.tvProfile.text = "${it.surName} ${it.name}"
                    }
                    binding.tvProfile.isVisible = it != null
                    binding.tvSignIn.isVisible = it == null
                }
            }
        } catch (e: Exception) {
            print(Log.d("myTAG", "PROFILE"))
        }

        val newslayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val menulayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            val institutes = firebaseMenuRepository.getMenu()
            Log.d("INSTITUTES", "RESULT $institutes ")
            menuAdapter.submitList(institutes)
        }

        val newsRv = binding.rvNews
        newsRv.layoutManager = newslayoutManager
        newsAdapter = NewsAdapter(requireContext()).also {
            newsRv.adapter = it
        }

        binding.tvProfile.setOnClickListener {
            findNavController().navigate(R.id.profileFragment).also {
                requireContext().clickSound()
            }
        }
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(requireContext(), AuthActivity::class.java)
            startActivity(intent).also {
                requireContext().clickSound()
            }
        }

        val menuRv = binding.rvInfo
        menuRv.layoutManager = menulayoutManager
        menuAdapter = MenuAdapter(requireContext()) { foundId ->
            menuList.find {
                it.id == foundId
            }?.let {
                requireContext().clickSound()
                when (it.type) {
                    ItemType.INSTITUTES -> {
                        findNavController().navigate(R.id.institutesFragment)
                    }

                    ItemType.ABOUT -> {
                        val args = Bundle()
                        args.putString(
                            WebViewFragment.ARG_URL,
                            "https://kpfu.ru/buklet-priema/mobile/"
                        )
                        findNavController().navigate(R.id.webViewFragment, args)
                    }

                    ItemType.CATCHING -> {
                        val args = Bundle()
                        args.putString(
                            WebViewFragment.ARG_URL,
                            "https://admissions.kpfu.ru/priem-v-universitet/sroki-provedeniya-priema-bakalavriat/specialitet-magistratura"
                        )
                        findNavController().navigate(R.id.webViewFragment, args)
                    }

                    ItemType.ENTERING -> {
                        findNavController().navigate(R.id.enteringFragment)
                    }

                }
            }
        }
        menuRv.adapter = menuAdapter
        lifecycleScope.launch {
            val news = firebaseNewsRepository.getNews()
            menuList = firebaseMenuRepository.getMenu()

            withContext(Dispatchers.Main) {
                newsAdapter.submitList(news)
                menuAdapter.submitList(menuList)
            }

        }

    }

    private fun observeConfiguration() {
        lifecycleScope.launch {
            featureUseCase.observeConfiguration().collect(::configure)
        }
    }

    private fun configure(configs: List<FeatureConfiguration>) {
        configs.forEach {
            with(binding) {
                when (it.feature) {
                    is Feature.News -> {
                        rvNews.isVisible = it.isEnabled
                    }

                    else -> {}
                }
            }
        }
    }

}