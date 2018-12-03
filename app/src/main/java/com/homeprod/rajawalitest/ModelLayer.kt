/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 22:00
 */

package com.homeprod.rajawalitest

import java.util.*

data class ModelLayer(
    var id: Int,
    var materialPack: MaterialPack,
    var fileName: String,
    var favorite: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ModelLayer

        return fileName == other.fileName && favorite == other.favorite
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(arrayOf(fileName, favorite))
    }

}