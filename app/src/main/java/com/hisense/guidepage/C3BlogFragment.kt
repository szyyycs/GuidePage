package com.hisense.guidepage

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatViewInflater
import androidx.core.os.bundleOf
import androidx.core.view.LayoutInflaterCompat
import androidx.fragment.app.Fragment
import java.lang.RuntimeException
/**
 * <pre>
 *     author : yangchaosheng
 *     e-mail : yangchaosheng@hisense.com
 *     time   : 2022/09/16
 *     desc   : fragment有一个instance方法，传入layoutid，然后存到bundle里，用的时候再拿出来
 *      然后在createview里生成一个存储标签的类，生成列表
 *
 * </pre>
 */
class C3BlogFragment private constructor():Fragment(),LayoutInflater.Factory2{
    companion object{
        @NonNull
        private const val LAYOUT_ID="layout_id"
        fun instance(@LayoutRes layoutId:Int)=let {
            C3BlogFragment().apply {
                arguments= bundleOf(LAYOUT_ID to layoutId)
            }
        }
    }
    val list= arrayListOf<View>()
    private val layoutId by lazy{
        arguments?.getInt(LAYOUT_ID)?:-1
    }
    private var mAppCompatViewInflater= SystemAppCompatViewInflater()




    override fun onCreateView(p0: View?, p1: String, p2: Context, p3: AttributeSet): View? {
        val view = createView(p0,p1,p2,p3)
        val data=C3Bean()
        (0 until p3.attributeCount).forEach {
            when (p3.getAttributeName(it)) {
                "parallaxTransformInX" -> {
                    data.parallaxTransformInX=p3.getAttributeValue(it).toFloat()
                }
                "parallaxTransformInY" -> {
                    data.parallaxTransformInY=p3.getAttributeValue(it).toFloat()
                }
                "parallaxTransformOutX" -> {
                    data.parallaxTransformOutX=p3.getAttributeValue(it).toFloat()
                }
                "parallaxTransformOutY" -> {
                    data.parallaxTransformOutY=p3.getAttributeValue(it).toFloat()

                }
                "parallaxRotate" -> {
                    data.parallaxRotate=p3.getAttributeValue(it).toFloat()

                }
                "parallaxZoom" -> {
                    data.parallaxZoom=p3.getAttributeValue(it).toFloat()

                }
            }
        }
        if(view!=null&&data.isNotEmpty()){
            if(view.id!=View.NO_ID){
                view.setTag(view.id,data)
                list.add(view)

            }else{
                throw RuntimeException("需要移动的view必须设置id来保证数据不会丢失..")
            }
        }
        return view
    }

    override fun onCreateView(p0: String, p1: Context, p2: AttributeSet): View? {
        return onCreateView(null,p0,p1,p2)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?,): View? {
        val newInflater=inflater.cloneInContext(activity)
        LayoutInflaterCompat.setFactory2(newInflater,this)
        return newInflater.inflate(layoutId,container,false)
    }
    private fun createView(parent:View?,name:String?,mContext:Context,attrs:AttributeSet):View?{
        val is21=Build.VERSION.SDK_INT<21
        return mAppCompatViewInflater
            .createView(parent, name, mContext, attrs, false,
            is21,
            true,
            false
        )
    }

}
