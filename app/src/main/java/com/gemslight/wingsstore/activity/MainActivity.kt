package com.gemslight.wingsstore.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gemslight.wingsstore.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_activity_main)
    }
}