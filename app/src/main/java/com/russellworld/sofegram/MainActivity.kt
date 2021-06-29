package com.russellworld.sofegram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.russellworld.sofegram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}