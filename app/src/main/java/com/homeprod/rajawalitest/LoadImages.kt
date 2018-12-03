/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:25
 */

package com.homeprod.rajawalitest

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.homeprod.rajawalitest.App.Companion.instance
import com.homeprod.rajawalitest.ext.subscribeOnIo
import io.reactivex.Observable

val TEXTURE_FOLDER = "textures"

class LoadImages {
    private val allPictures = mutableListOf<Picture>()
    val allPicturePack = mutableListOf<PicturePack>()

    fun loadBitmaps(): Observable<Picture> =
        Observable.just(instance.assets.list(TEXTURE_FOLDER))
            .subscribeOnIo()
            .concatMapIterable { it.toList() }
            .map { loadSinglePicture(it) }

    fun loadSinglePicture(fileName: String): Picture {
        var bitmapOriginal: Bitmap? = null
        instance.assets.open("${TEXTURE_FOLDER}/$fileName").use { input ->
            bitmapOriginal = BitmapFactory.decodeStream(input, null, BitmapFactory.Options().apply { inScaled = false })
        }
        bitmapOriginal = (bitmapOriginal
            ?: Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888))
        return Picture(fileName, bitmapOriginal!!).also {
            allPictures.add(it)
        }
    }

    fun cutImagePack(picture: Picture): PicturePack =
        PicturePack(picture.fileName, picture.bitmap).also { allPicturePack.add(it) }
}