package com.example.webapponmaptiler

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val APIKEY = "gpVOgqbHwdAs6xGA7G9m#15/55.76758/37.68705"
    private lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        myWebView = findViewById(R.id.webview)

        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("https://api.maptiler.com/maps/0195e431-1711-7754-95d8-125ec2429b38/?key=${APIKEY}")
    }
}