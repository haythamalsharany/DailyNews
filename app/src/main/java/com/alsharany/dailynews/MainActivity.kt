package com.alsharany.dailynews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var adabter: PagerAdabter
    companion object{
          var categoryId:Int=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.main_tab_layout)
        viewPager = findViewById(R.id.main_view_Pager)
        adabter = PagerAdabter(this)
        adabter.addTab(MyTab("general news", GeneralNewsList.newInstance(0)))
        adabter.addTab(MyTab("policy news", GeneralNewsList.newInstance(1)))
        adabter.addTab(MyTab("sports news", GeneralNewsList.newInstance(2)))
        adabter.addTab(MyTab("economic news", GeneralNewsList.newInstance(3)))
        adabter.addTab(MyTab("other news", GeneralNewsList.newInstance(4)))
        viewPager.adapter = adabter
        TabLayoutMediator(tabLayout, viewPager) { tap, postion ->
            when (postion) {
                0 -> {

                    tap.text ="general"
                }
                1 -> {
                    tap.text = "policy"
                }

                2 -> {
                    tap.text = "sports"
                }

               3 -> {
                    tap.text = "economic"
                }

                4 -> {
                    tap.text = "diffirence"
                }
                else -> tap.text = "other"
            }


        }.attach()

}}