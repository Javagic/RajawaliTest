/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 06.08.18 22:06
 */
package com.homeprod.rajawalitest.ext

import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import org.rajawali3d.view.SurfaceView

@Suppress("UNCHECKED_CAST")
fun <T : View> View.view(@IdRes id: Int) = lazy { findViewById<T>(id) as T }

fun View.textView(@IdRes idRes: Int) = view<TextView>(idRes)

fun View.imageView(@IdRes idRes: Int) = view<ImageView>(idRes)

fun View.surfaceView(@IdRes idRes: Int) = view<SurfaceView>(idRes)
fun View.button(@IdRes idRes: Int) = view<Button>(idRes)
fun View.constraintLayout(@IdRes idRes: Int) = view<ConstraintLayout>(idRes)
fun View.frameLayout(@IdRes idRes: Int) = view<FrameLayout>(idRes)


inline fun <V : View> View.view(@IdRes idRes: Int, crossinline initializer: (V) -> Unit) =
    lazy { findViewById<V>(idRes).apply { initializer(this) } }