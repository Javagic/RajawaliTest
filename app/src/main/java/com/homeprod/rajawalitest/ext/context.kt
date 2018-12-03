/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 22.08.18 16:53
 */
package com.homeprod.rajawalitest.ext

import android.content.Context

const val PREFERENCES = "shared_preference"

fun Context.preferences() = this.getSharedPreferences(PREFERENCES, 0)