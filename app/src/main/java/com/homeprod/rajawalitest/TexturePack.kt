/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:15
 */

package com.homeprod.rajawalitest

import org.rajawali3d.materials.textures.ATexture
import org.rajawali3d.materials.textures.Texture
import org.rajawali3d.materials.textures.TextureManager


const val TEXTURE_LEFT_INNER = "materialLeftInner"
const val TEXTURE_RIGHT_INNER = "materialRightInner"
const val TEXTURE_LEFT_OUTER = "materialLeftOuter"
const val TEXTURE_RIGHT_OUTER = "materialRightOuter"


class TexturePack(val id: Int, picturePack: PicturePack) {
    var textureLeftInner = Texture(TEXTURE_LEFT_INNER + id, picturePack.textureLeftInner)
    var textureRightInner = Texture(TEXTURE_RIGHT_INNER + id, picturePack.textureRightInner)
    var textureLeftOuter = Texture(TEXTURE_LEFT_OUTER + id, picturePack.textureLeftOuter)
    var textureRightOuter = Texture(TEXTURE_RIGHT_OUTER + id, picturePack.textureRightOuter)

    init {
        textureLeftInner.filterType = ATexture.FilterType.NEAREST
        textureRightInner.filterType = ATexture.FilterType.NEAREST
        textureLeftOuter.filterType = ATexture.FilterType.NEAREST
        textureRightOuter.filterType = ATexture.FilterType.NEAREST
    }
}

private operator fun Int.plus(str: String): String {
    return this.toString() + str
}
