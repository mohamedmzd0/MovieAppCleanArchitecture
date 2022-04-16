package com.mohamed.movieappcleanarchitecture.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        /// set localization
    }


    open fun setupObserver() {}
    open fun showLoading() {}
    open fun cancelLoading() {}

}