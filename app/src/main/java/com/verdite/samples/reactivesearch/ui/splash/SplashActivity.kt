package com.verdite.samples.reactivesearch.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.verdite.samples.reactivesearch.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}