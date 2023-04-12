package com.book.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.book.R
import com.book.databinding.ActivityBookWebsiteBinding
import com.book.models.Book


class BookWebsiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookWebsiteBinding

    private var book: Book? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookWebsiteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.itemIconTintList = null

        if (intent.extras != null) {
            book = intent.getSerializableExtra("book", Book::class.java)
        }

        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                binding.toolbarUrl.text = url
                super.onPageFinished(view, url)
            }
        }

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.loadUrl(book?.url ?: "")
        binding.toolbarUrl.text = book?.url ?: ""

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.back -> {
                    if (binding.webview.canGoBack()) {
                        binding.webview.goBack()
                        binding.toolbarUrl.text = binding.webview.url
                    }
                }

                R.id.refresh -> binding.webview.reload()

                R.id.forward -> {
                    if (binding.webview.canGoForward()) {
                        binding.webview.goForward()
                        binding.toolbarUrl.text = binding.webview.url
                    }
                }
                else -> {
                    Toast.makeText(this, "Unknown", Toast.LENGTH_SHORT).show()
                }
            }
            return@setOnItemSelectedListener true

        }


    }
}