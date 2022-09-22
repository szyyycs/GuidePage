package com.hisense.guidepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        guide_btn.setOnClickListener {
            startActivity(Intent(this, GuideActivity::class.java))

        }
        custom_view.setOnClickListener {
            startActivity(Intent(this, CustomViewActivity::class.java))
        }
    }
}