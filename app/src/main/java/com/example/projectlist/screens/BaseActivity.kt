package com.example.projectlist.screens

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var baseViewModel: BaseViewModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

    }

    fun setBaseViewModel(baseViewModel: BaseViewModel?) {
        this.baseViewModel = baseViewModel
    }

}
