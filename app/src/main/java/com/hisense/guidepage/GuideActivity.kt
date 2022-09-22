package com.hisense.guidepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)
        viewPager.setLayout(
                supportFragmentManager,
                arrayListOf(
                        R.layout.fragment1,
                        R.layout.fragment2,
                        R.layout.fragment3
                )
        )
    }
}