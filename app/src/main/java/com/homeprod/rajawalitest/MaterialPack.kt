/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:16
 */

package com.homeprod.rajawalitest

import org.rajawali3d.materials.Material
import org.rajawali3d.materials.plugins.AlphaMaskMaterialPlugin
import org.rajawali3d.materials.textures.Texture


class MaterialPack(val id: Int, texturePack: TexturePack) {
    var materialLeftInner = material(texturePack.textureLeftInner)
    var materialRightInner = material(texturePack.textureRightInner)
    var materialLeftOuter = material(texturePack.textureLeftOuter)
    var materialRightOuter = material(texturePack.textureRightOuter)


    private fun material(texture: Texture) =
        Material().apply {
            addTexture(texture)
            addPlugin(AlphaMaskMaterialPlugin(0.8f))
            color = 0
            colorInfluence = 1.0f
        }

}