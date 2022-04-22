package com.mohamed.movieappcleanarchitecture.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.mohamed.movieappcleanarchitecture.R
import com.mohamed.movieappcleanarchitecture.databinding.ActivitySplashBinding
import com.mohamed.movieappcleanarchitecture.presentation.base.BaseActivity
import com.mohamed.movieappcleanarchitecture.presentation.home.MainActivity

private const val SPLASH_TIME = 1500L

class SplashActivity : BaseActivity() {


    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var binding: ActivitySplashBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_splash, null, false)
        setContentView(binding?.root)
        setupHandler()


    }

    private fun setupHandler() {
        handler = Handler(Looper.myLooper()!!)
        runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }

    override fun onStart() {
        super.onStart()
        runnable?.let { handler?.postDelayed(it, SPLASH_TIME) }
    }

    override fun onStop() {
        super.onStop()
        runnable?.let { handler?.removeCallbacks(it) }
    }


    override fun onDestroy() {
        super.onDestroy()
        // remove memory reference on destroy
        binding = null
    }
}