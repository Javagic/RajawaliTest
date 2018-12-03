/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 08.08.18 19:43
 */
package com.homeprod.rajawalitest.ext

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import android.support.annotation.IdRes
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.rajawali3d.view.SurfaceView

fun <V : View> Activity.view(@IdRes idRes: Int) = lazy { findViewById<V>(idRes) }

fun Activity.textView(@IdRes idRes: Int) = view<TextView>(idRes)

fun Activity.imageView(@IdRes idRes: Int) = view<ImageView>(idRes)

fun Activity.surfaceView(@IdRes idRes: Int) = view<SurfaceView>(idRes)
fun Activity.constraintLayout(@IdRes idRes: Int) = view<ConstraintLayout>(idRes)
