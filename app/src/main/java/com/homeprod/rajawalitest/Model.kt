/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:17
 */

package com.homeprod.rajawalitest

import android.graphics.Bitmap
import io.reactivex.Observable
import org.rajawali3d.Object3D
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.textures.Texture
import org.rajawali3d.materials.textures.TextureManager
import org.rajawali3d.math.vector.Vector3


const val INNATE_DEVIATION_Y = 4.5

class Model(obj: Object3D, val position: Int) {
    var isShown: Boolean = false
    var innerSurface: Object3D
    var outerSurface: Object3D
    private var textureLeftInner: Texture
    private var textureRightInner: Texture
    private var textureLeftOuter: Texture
    private var textureRightOuter: Texture
    private var materialLeftInner: Material
    private var materialRightInner: Material
    private var materialLeftOuter: Material
    private var materialRightOuter: Material

    init {
        val texturePack = TexturePack(position, EMPTY_PICTURE_PACK)
        textureLeftInner = texturePack.textureLeftInner
        textureRightInner = texturePack.textureRightInner
        textureLeftOuter = texturePack.textureLeftOuter
        textureRightOuter = texturePack.textureRightOuter
        innerSurface = obj.clone(false, true)
        outerSurface = obj.clone(false, true)
        val materialPack = MaterialPack(position, texturePack)
        setMaterialPack(materialPack)
        materialLeftInner = materialPack.materialLeftInner
        materialRightInner = materialPack.materialRightInner
        materialLeftOuter = materialPack.materialLeftOuter
        materialRightOuter = materialPack.materialRightOuter
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

    fun setPicturePack(picturePack: PicturePack) {
        textureLeftInner = setTex(materialLeftInner, textureLeftInner, picturePack.textureLeftInner)
        textureRightInner = setTex(materialRightInner, textureRightInner, picturePack.textureRightInner)
        textureLeftOuter = setTex(materialLeftOuter, textureLeftOuter, picturePack.textureLeftOuter)
        textureRightOuter = setTex(materialRightOuter, textureRightOuter, picturePack.textureRightOuter)
    }

    private fun setTex(mat: Material, tex: Texture, pic: Bitmap): Texture {
        mat.removeTexture(tex)
        Observable.fromCallable { TextureManager.getInstance().removeTexture(tex) }.subscribe()
        tex.bitmap = pic
        val newTex = Texture(tex)
        mat.addTexture(newTex)
        return newTex
    }

    fun setMaterialPack(materialPack: MaterialPack) {
        setMaterial(outerSurface, materialPack.materialLeftOuter, materialPack.materialRightOuter)
        setMaterial(innerSurface, materialPack.materialLeftInner, materialPack.materialRightInner)
    }

    private fun setMaterial(obj: Object3D, materialLeft: Material, materialRight: Material) {
        setMaterial(obj, materialLeft, true)
        setMaterial(obj, materialRight, false)
        obj.material = materialRight
    }

    private fun setMaterial(obj: Object3D, material: Material, isLeftPartsOfModel: Boolean) {
        val partNames = listOf("Steve_LLeg", "Steve_Body", "Steve_LArm", "Steve_Head")
        for (i in 0 until obj.numChildren) {
            if (partNames.contains(obj.getChildAt(i).name) == isLeftPartsOfModel) {
                obj.getChildAt(i).material = material
            }
        }
    }
}