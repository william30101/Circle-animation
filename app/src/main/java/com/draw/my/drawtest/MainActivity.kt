package com.draw.my.drawtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWebView()
    }

    fun initWebView() {
        val webSettings = my_webview.settings
        webSettings.javaScriptEnabled = true
        my_webview.webViewClient = WebViewClient()
        my_webview.loadUrl("https://www.google.com.tw")
    }


}
