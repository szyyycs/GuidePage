package com.hisense.guidepage

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * <pre>
 *     author : yangchaosheng
 *     e-mail : yangchaosheng@hisense.com
 *     time   : 2022/09/19
 *     desc   :
 * </pre>
 */
class MyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleInt: Int = 0
) : View(context, attributeSet, defStyleInt) {
    companion object{
        const val COUNT=100
        val RADIUS=200.dp
    }
    private val startPointF by lazy {
        PointF(width / 2f, height / 2f)
    }
    private val controlPointF=PointF(100.dp,100.dp)


    val paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.strokeWidth = 2.dp
        it.color = Color.BLACK
        it.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        paint.style=Paint.Style.STROKE
        repeat(COUNT){
            paint.color=paths[it].first
            canvas.drawPath(paths[it].second,paint)
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        secondListBezierAnimator()
    }
    private val paths= arrayListOf<Pair<Int,Path>>()
//    private fun animator() {
//        val p0 = PointF(50.dp, 100.dp)
//        val p1 = PointF(100.dp, 50.dp)
//        val p2 = PointF(150.dp, 100.dp)
//        var angle=0.0
//
//        val animator =
//            ObjectAnimator.ofObject(this,
//                "pointF",
//                SecondBezierTypeEvaluator(p1),
//                p0,
//                p2)
//        path.moveTo(p0.x, p0.y)
//        animator.duration = 2000L
//        animator.start()
//    }
    private val colorRandom: Int
        get() {
            return Color.argb(
                255,
                (200 until 255).random(),
                (200 until 255).random(),
                (0 until 255).random()
            )
        }

    private fun secondListBezierAnimator(){
        val p0= arrayListOf<PointF>()
        val p1= arrayListOf<PointF>()
        val p2= arrayListOf<PointF>()
        var angle=0.0
        repeat(COUNT){
            p0.add(startPointF)
            p1.add(controlPointF)
            val x= RADIUS*sin(Math.toRadians(angle))+
                    width/2f
            val y= RADIUS*cos(Math.toRadians(angle))+
                    height/2f
            p2.add(PointF(x.toFloat(),y.toFloat()))

            angle+=360.0/ COUNT
            val path=Path()
            path.moveTo(p0[it].x,p0[it].y)
            paths.add(colorRandom to path)
        }
        val animator = ObjectAnimator.ofObject(
            this,
            "points",
            SecondBezierTypeEvaluator(p1),
            p0,
            p2
        )
        animator.duration = 2000L
        animator.start()

    }
}