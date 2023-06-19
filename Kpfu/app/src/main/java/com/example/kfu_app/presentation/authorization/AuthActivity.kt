package com.example.kfu_app.presentation.authorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.presentation.common.AppActivity
import com.example.kfu_app.R
import com.example.kfu_app.databinding.ActivityAuthBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var viewModel: AuthorizationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApplicationDelegate.getScreenComponent().inject(this)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLiveDataListeners()
        Log.d("MYTAG", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MYTAG", "onStart")
    }

    private fun initLiveDataListeners() {
        lifecycleScope.launch {
            viewModel.isUserAuthenticated().collect() {
                if (it) {
                    onAuthenticated()
                } else {
                    navigateToAuthentication()
                }
            }
        }

    }

    private fun navigateToAuthentication() {
        findNavController(R.id.hostAuthFragment).navigate(R.id.signInFragment)
    }

    private fun onAuthenticated() {
        val intent = Intent(this, AppActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }
}