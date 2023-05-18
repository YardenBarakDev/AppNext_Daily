package com.ybdev.appnextdaily.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ybdev.appnextdaily.R

class MainActivity : AppCompatActivity() {

    //private val weatherViewModel by viewModel<DailyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}