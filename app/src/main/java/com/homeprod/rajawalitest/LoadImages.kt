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
import io.reactivex.Completable
import io.reactivex.Observable
import kotlin.math.max
import kotlin.math.min

val TEXTURE_FOLDER = "textures"
val MODELS_COUNT = 4
val EMPTY_PICTURE_PACK by lazy {
    LoadImages.cutImagePack(Picture("-1", BitmapFactory.decodeResource(instance.resources, R.drawable.stub)))
}

object LoadImages {
    private val allPictures = mutableListOf<Picture>()
    private val allPacks = mutableListOf<PicturePack>()

    fun loadBitmaps(): Completable =
        Observable.just(instance.assets.list(TEXTURE_FOLDER))
            .subscribeOnIo()
            .concatMapIterable { it.toList() }
            .map { loadSinglePicture(it) }
            .doOnNext { allPacks.add(cutImagePack(it)) }
            .ignoreElements()

    private fun loadSinglePicture(fileName: String): Picture {
        var bitmapOriginal: Bitmap? = null
        instance.assets.open("$TEXTURE_FOLDER/$fileName").use { input ->
            bitmapOriginal = BitmapFactory.decodeStream(input, null, BitmapFactory.Options().apply { inScaled = false })
        }
        bitmapOriginal = (bitmapOriginal
            ?: Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888))
        return Picture(fileName, bitmapOriginal!!).also {
            allPictures.add(it)
        }
    }

    fun cutImagePack(picture: Picture): PicturePack =
        PicturePack(picture.fileName, picture.bitmap)


    fun getModels(page: Int): Observable<List<PicturePack>> =
        Observable.fromCallable {
            allPacks.slice(
                max(MODELS_COUNT * (page - 1), 0) until
                        min((page * MODELS_COUNT), allPacks.size)
            )
        }
            .subscribeOnIo()


}