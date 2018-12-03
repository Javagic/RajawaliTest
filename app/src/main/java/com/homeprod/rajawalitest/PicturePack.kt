/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:15
 */

package com.homeprod.rajawalitest

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable

class PicturePack(val fileName: String, bitmapOriginal: Bitmap) {
    var textureLeftInner = bitmapOriginal
    var textureRightInner = bitmapOriginal
    var textureLeftOuter = bitmapOriginal
    var textureRightOuter = bitmapOriginal

    init {
        run {
            if (bitmapOriginal.height != 64) {
                var transparentBitmap =
                    Bitmap.createBitmap(bitmapOriginal.width, bitmapOriginal.height, bitmapOriginal.config)
                val bd = BitmapDrawable(transparentBitmap)
                bd.alpha = 50
                transparentBitmap = bd.bitmap
                textureLeftOuter = transparentBitmap
                textureRightOuter = transparentBitmap.copy(transparentBitmap.config, true)
                return@run
            }

            //Левая внутренняя
            textureLeftInner = Bitmap.createBitmap(bitmapOriginal, 0, 0, 64, 32)
            //Правая внутренняя
            val bitmapRightInner =
                Bitmap.createBitmap(bitmapOriginal.width, bitmapOriginal.height, bitmapOriginal.config)
            var canvas = Canvas(bitmapRightInner)
            //cut right leg inner
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 16, 48, 16, 16),
                Rect(0, 0, 16, 16),
                Rect(0, 16, 16, 32), null
            )
            //cut right arm inner
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 32, 48, 16, 16),
                Rect(0, 0, 16, 16),
                Rect(40, 16, 56, 32), null
            )
            //cut head inner
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 0, 0, 32, 16),
                Rect(0, 0, 32, 16),
                Rect(0, 0, 32, 16), null
            )
            //cut body inner
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 16, 16, 24, 16),
                Rect(0, 0, 24, 16),
                Rect(16, 16, 40, 32), null
            )
            textureRightInner =
                    Bitmap.createBitmap(bitmapRightInner, 0, 0, 64, 32)
            //
            val bitmapLeftOuter =
                Bitmap.createBitmap(bitmapOriginal.width, bitmapOriginal.height, bitmapOriginal.config)
            canvas = Canvas(bitmapLeftOuter)
            //outer left leg arm body
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 0, 32, 64, 16),
                Rect(0, 0, 64, 16),
                Rect(0, 16, 64, 32), null
            )
            //head outer
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 32, 0, 32, 16),
                Rect(0, 0, 32, 16),
                Rect(0, 0, 32, 16), null
            )
            textureLeftOuter = Bitmap.createBitmap(bitmapLeftOuter, 0, 0, 64, 32)

            val bitmapRightOuter =
                Bitmap.createBitmap(bitmapOriginal.width, bitmapOriginal.height, bitmapOriginal.config)
            canvas = Canvas(bitmapRightOuter)
            //body outer
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 16, 32, 24, 16),
                Rect(0, 0, 24, 16),
                Rect(16, 16, 40, 32), null
            )
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 0, 48, 16, 16),
                Rect(0, 0, 16, 16),
                Rect(0, 16, 16, 32), null
            )
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 48, 48, 16, 16),
                Rect(0, 0, 16, 16),
                Rect(40, 16, 56, 32), null
            )
            canvas.drawBitmap(
                Bitmap.createBitmap(bitmapOriginal, 32, 0, 32, 16),
                Rect(0, 0, 32, 16),
                Rect(0, 0, 32, 16), null
            )
            textureRightOuter = Bitmap.createBitmap(bitmapRightOuter, 0, 0, 64, 32)
        }
    }
}