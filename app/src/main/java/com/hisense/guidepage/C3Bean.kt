package com.hisense.guidepage

import androidx.annotation.FloatRange

/**
 * <pre>
 *     author : yangchaosheng
 *     e-mail : yangchaosheng@hisense.com
 *     time   : 2022/09/16
 *     desc   :
 * </pre>
 */
data class C3Bean(
        @FloatRange(from = 0.0,to=1.0) var parallaxTransformInX:Float=0.0f,
        @FloatRange(from = 0.0,to=1.0) var parallaxTransformInY:Float=0.0f,
        @FloatRange(from = 0.0,to=1.0) var parallaxTransformOutX:Float=0.0f,
        @FloatRange(from = 0.0,to=1.0) var parallaxTransformOutY:Float=0.0f,
        @FloatRange(from = 0.0) var parallaxRotate:Float=0f,
        @FloatRange(from = 0.0) var parallaxZoom:Float=0f,
) {
    fun isNotEmpty()=let{
        parallaxTransformInX!=0f
                || parallaxTransformInY!=0f
                || parallaxTransformOutX!=0f
                || parallaxTransformOutY!=0f
                || parallaxRotate!=0f
                || parallaxZoom!=0f
    }

}