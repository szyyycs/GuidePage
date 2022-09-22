package com.hisense.guidepage

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * <pre>
 *     author : yangchaosheng
 *     e-mail : yangchaosheng@hisense.com
 *     time   : 2022/09/16
 *     desc   :这个就是自定义一个viewpager，函数有个setlayout，对fragment进行分发设置，
 *     然后监听本身的pagechange，在change的时候获取所有view，还有它里边设置的布局属性getTag
 *     设置translationX，translationY等动画属性。。。
 *
 *
 * </pre>
 */
class ParallaxBlogViewPager(context: Context,attrs:AttributeSet?):ViewPager(context, attrs) {
    lateinit var listFragment:ArrayList<C3BlogFragment>

    fun setLayout(fragmentManager: FragmentManager,@LayoutRes list: ArrayList<Int>){
        listFragment= arrayListOf<C3BlogFragment>()
        list.map{
            C3BlogFragment.instance(it)
        }.forEach {
            listFragment.add(it)
        }
        adapter=ParallaxBlockAdapter(listFragment,fragmentManager)
        addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val currentFragment: C3BlogFragment = listFragment[position]

                currentFragment.list.forEach { view ->
                    // 获取到tag中的值
                    val tag = view.getTag(view.id)

                    (tag as? C3Bean)?.also {
                        // 入场
                        view.translationX = -it.parallaxTransformInX * positionOffsetPixels
                        view.translationY = -it.parallaxTransformInY * positionOffsetPixels
                        view.rotation = -it.parallaxRotate * 360 * positionOffset


                        view.scaleX =
                                1 + it.parallaxZoom - (it.parallaxZoom * positionOffset)
                        view.scaleY =
                                1 + it.parallaxZoom - (it.parallaxZoom * positionOffset)

                    }
                }
                if (position + 1 < listFragment.size) {
                    val nextFragment = listFragment[position + 1]
                    nextFragment.list.forEach { view ->
                        val tag = view.getTag(view.id)
                        (tag as? C3Bean)?.also {
                            view.translationX =
                                    it.parallaxTransformInX * (width - positionOffsetPixels)
                            view.translationY =
                                    it.parallaxTransformInY * height *(width - positionOffsetPixels)/width

                            view.rotation = it.parallaxRotate * 360 * positionOffset

                            view.scaleX = (1 + it.parallaxZoom * positionOffset)

                            view.scaleY = (1 + it.parallaxZoom * positionOffset)

                        }
                    }
                }
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }
    private inner class ParallaxBlockAdapter(
        private val list:List<Fragment>,
        fm:FragmentManager
    ):FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return list.size
        }
        override fun getItem(position: Int): Fragment
        = list[position]
    }



}