/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:59
 */

package com.homeprod.rajawalitest

import android.graphics.Bitmap
import java.util.*


data class Picture(val fileName: String, val bitmap: Bitmap) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as ModelLayer
        return fileName == other.fileName
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(arrayOf(fileName))
    }
}