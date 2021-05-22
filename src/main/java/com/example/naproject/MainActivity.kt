package com.example.naproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.naproject.feature.mainMenu.ui.DisplaysFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
                .add(R.id.container, DisplaysFragment())
                .commit()
    }
}