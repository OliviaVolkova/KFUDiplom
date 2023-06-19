package com.example.kfu_app.presentation.common

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.R
import com.example.kfu_app.databinding.ActivityMainBinding
import com.example.kfu_app.databinding.FragmentNavDrawerBinding
import com.example.kfu_app.domain.model.Feature
import com.example.kfu_app.domain.model.FeatureConfiguration
import com.example.kfu_app.domain.usecases.FeatureUseCase
import com.example.kfu_app.presentation.authorization.AuthorizationViewModel
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppActivity : AppCompatActivity(), NavigationDrawerManager {

    private lateinit var binding: ActivityMainBinding
    lateinit var headerBinding: FragmentNavDrawerBinding

    @Inject
    lateinit var viewModel: AuthorizationViewModel
    lateinit var navController: NavController

    @Inject
    lateinit var useCase: FeatureUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val sideBar = binding.navView
        val headerView = initDrawer()

        sideBar.addHeaderView(headerView)
        sideBar.setupWithNavController(navController)

        ApplicationDelegate.getScreenComponent().inject(this)
        initLiveDataListeners()
        observeConfiguration()
        Log.d("MYTAG", "onCreate")

    }

    fun initDrawer(): View {
        headerBinding = FragmentNavDrawerBinding.inflate(layoutInflater)
        with(headerBinding) {
            ivVk.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/priemkpfu"))
                startActivity(intent)
            }
            ivTg.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/priemkpfu"))
                startActivity(intent)
            }
            ivTgChat.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/priemkpfusng"))
                startActivity(intent)
            }
            headerBinding.containerContacts.setOnClickListener {
                navController.navigate(R.id.contactsFragment)
                binding.root.closeDrawers().also {
                    applicationContext.clickSound()
                }
            }
            headerBinding.containerProfile.setOnClickListener {
                navController.navigate(R.id.profileFragment)
                binding.root.closeDrawers().also {
                    applicationContext.clickSound()
                }
            }
            headerBinding.containerStatement.setOnClickListener {
                navController.navigate(R.id.statementsFragment)
                binding.root.closeDrawers().also {
                    applicationContext.clickSound()
                }
            }
            headerBinding.containerMenu.setOnClickListener {
                navController.navigate(R.id.fragmentMenu)
                binding.root.closeDrawers().also {
                    applicationContext.clickSound()
                }
            }
            headerBinding.containerSchedule.setOnClickListener {
                navController.navigate(R.id.scheduleFragment)
                binding.root.closeDrawers().also {
                    applicationContext.clickSound()
                }
            }
        }
        return headerBinding.root
    }

    private fun initLiveDataListeners() {
        lifecycleScope.launch {
            viewModel.isUserAuthenticated().collect() {
//                if (!it) {
//                    val intent = Intent(this@AppActivity, AuthActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
            }
        }

    }

    private fun observeConfiguration() {
        lifecycleScope.launch {
            useCase.observeConfiguration().collect(::configure)
        }
    }

    private fun configure(configs: List<FeatureConfiguration>) {
        configs.forEach {
            with(headerBinding) {
                when (it.feature) {
                    is Feature.Profile -> {
                        containerProfile.isVisible = it.isEnabled
                    }

                    is Feature.Statements -> {
                        containerStatement.isVisible = it.isEnabled
                    }

                    is Feature.Schedule -> {
                        containerSchedule.isVisible = it.isEnabled
                    }

                    is Feature.Contacts -> {
                        containerContacts.isVisible = it.isEnabled
                    }

                    else -> {}
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findNavController(R.id.nav_host_fragment).navigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MYTAG", "onStart")
    }

    override fun showDrawer() {
        binding.root.openDrawer(binding.navView)
    }

    override fun hideDrawer() {
        binding.root.closeDrawers()
    }

}