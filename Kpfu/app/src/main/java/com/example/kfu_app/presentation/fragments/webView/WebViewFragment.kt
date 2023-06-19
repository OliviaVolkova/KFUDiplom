package com.example.kfu_app.presentation.fragments.webView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kfu_app.databinding.FragmentWebviewBinding
import com.example.kfu_app.utils.clickSound

class WebViewFragment : Fragment(){

    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(ARG_URL)?.let {
            openWebView(it)
        }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }
    }

    companion object {
        const val ARG_URL = "WEB_VIEW_URL"
    }


    fun openWebView(url: String){
        val myWebView: WebView = binding.webview
        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.webViewClient = WebViewClient()
        myWebView.loadUrl(url)

//
//        val intent = Intent(
//                        Intent.ACTION_INSERT,
//                        Uri.parse(myWebView.url))
//                        startActivity(intent)
    }
}