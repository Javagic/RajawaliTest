/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:17
 */

package com.homeprod.rajawalitest

import org.rajawali3d.Object3D
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.MaterialManager
import org.rajawali3d.materials.textures.TextureManager
import org.rajawali3d.math.vector.Vector3

const val INNATE_DEVIATION_Y = 4.5

class Model(val position: Int) {
    var isShown: Boolean = false
    lateinit var innerSurface: Object3D
    lateinit var outerSurface: Object3D

    init {
        val texturePack = TexturePack(position, EMPTY_PICTURE_PACK)
        TextureManager.getInstance().apply {
            addTexture(texturePack.textureLeftInner)
            addTexture(texturePack.textureRightInner)
            addTexture(texturePack.textureLeftOuter)
            addTexture(texturePack.textureRightOuter)
        }
        val materialPack = MaterialPack(position, texturePack)
        setMaterialPack(materialPack)
        MaterialManager.getInstance()?.apply {
            addMaterial(materialPack.materialLeftInner)
            addMaterial(materialPack.materialRightInner)
            addMaterial(materialPack.materialLeftOuter)
            addMaterial(materialPack.materialRightOuter)
        }
    }

    fun build(x: Double, y: Double) {
        innerSurface.setPosition(x, y - INNATE_DEVIATION_Y, 0.0)
        outerSurface.setPosition(x, y - INNATE_DEVIATION_Y, 0.0)
        innerSurface.isTransparent = true
        outerSurface.run {
            scaleX = 1.05
            scaleY = 1.05
            scaleZ = 1.05
        }
    }

    fun render(deltaTime: Double) {
        val angle = -50f
        val vector3 = Vector3(0.0, 1.0, 0.0)
        innerSurface.rotateAround(vector3, -angle * deltaTime)
        outerSurface.rotateAround(vector3, -angle * deltaTime)
    }

    fun setTexturePack(texturePack: TexturePack) {
        TextureManager.getInstance().apply {
            replaceTexture(texturePack.textureLeftInner)
            replaceTexture(texturePack.textureRightInner)
            replaceTexture(texturePack.textureLeftOuter)
            replaceTexture(texturePack.textureRightOuter)
        }
    }

    fun setMaterialPack(materialPack: MaterialPack) {
        setMaterial(outerSurface, materialPack.materialLeftOuter, materialPack.materialRightOuter)
        setMaterial(innerSurface, materialPack.materialLeftInner, materialPack.materialRightInner)
    }

    private fun setMaterial(obj: Object3D, textureLeft: Material, textureRight: Material) {
        setMaterial(obj, textureLeft, true)
        setMaterial(obj, textureRight, false)
    }

    private fun setMaterial(obj: Object3D, material: Material, isLeftPartsOfModel: Boolean) {
        val partNames = listOf("Steve_LLeg", "Steve_Body", "Steve_LArm", "Steve_Head")
        for (i in 0 until obj.numChildren) {
            if (partNames.contains(obj.getChildAt(i).name) == isLeftPartsOfModel) {
                obj.getChildAt(i).material = material
            }
        }
        obj.material = material
    }
}