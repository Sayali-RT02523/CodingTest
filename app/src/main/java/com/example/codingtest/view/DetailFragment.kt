package com.example.codingtest.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.codingtest.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val url = arguments?.getString("url")


        binding.webView.webViewClient = WebViewClient()
        if (url != null) {
            binding.webView.loadUrl(url)
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)

        return binding.root

    }

}