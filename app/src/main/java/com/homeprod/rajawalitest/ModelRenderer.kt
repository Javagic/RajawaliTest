/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 03.12.18 21:08
 */

package com.homeprod.rajawalitest

import android.content.Context
import android.view.MotionEvent
import org.rajawali3d.loader.LoaderOBJ
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.textures.ATexture.TextureException
import org.rajawali3d.materials.textures.Texture
import org.rajawali3d.materials.textures.TextureManager
import org.rajawali3d.primitives.ScreenQuad
import org.rajawali3d.renderer.Renderer


const val MODEL_DISTANCE_X = 4.0
const val MODEL_LOW_DISTANCE_Y = -8.0
const val MODEL_HIGH_DISTANCE_Y = 8.0

class ModelRenderer(context: Context, private val action: () -> Unit) : Renderer(context, true) {

    private val renderModels by lazy {
        { deltaTime: Double ->
            models.forEach {
                it.render(deltaTime)
            }
        }
    }

    private val models by lazy {
        listOf(
            createModel(0, -MODEL_DISTANCE_X, MODEL_HIGH_DISTANCE_Y).apply { add() },
            createModel(1, +MODEL_DISTANCE_X, MODEL_HIGH_DISTANCE_Y).apply { add() },
            createModel(2, -MODEL_DISTANCE_X, MODEL_LOW_DISTANCE_Y).apply { add() },
            createModel(3, +MODEL_DISTANCE_X, MODEL_LOW_DISTANCE_Y).apply { add() }
        )
    }

    private val obj by lazy {
        LoaderOBJ(context.resources, TextureManager.getInstance(), R.raw.steve_obj)
            .parse()
            .parsedObject
    }

    override fun onRender(ellapsedRealtime: Long, deltaTime: Double) {
        super.onRender(ellapsedRealtime, deltaTime)
        renderModels(deltaTime)
    }

    private fun createModel(position: Int, x: Double, y: Double): Model {
        return Model(obj, position).apply {
            build(x, y)
        }

    }

    fun setPacks(materialPacks: List<PicturePack>) {
        models.forEachIndexed { i, model ->
            if (materialPacks.size > i) {
                model.setPicturePack(materialPacks[i])
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?) {}

    override fun initScene() {
        action()
        currentCamera.z = 40.0
        val mPlane1 = ScreenQuad(20, 20)
        val material1 = Material()
        try {
            material1.addTexture(Texture("cloud2", R.drawable.background))
        } catch (e: TextureException) {
            e.printStackTrace()
        }
        material1.colorInfluence = 0f
        mPlane1.material = material1
        mPlane1.setPosition(0.0, 0.0, -100.0)
        currentScene.addChild(mPlane1)
        currentScene.addChild(mPlane1)
    }

    override fun onOffsetsChanged(
        xOffset: Float,
        yOffset: Float,
        xOffsetStep: Float,
        yOffsetStep: Float,
        xPixelOffset: Int,
        yPixelOffset: Int
    ) {
    }

    private fun Model.remove(): Unit = if (isShown) {
        currentScene.removeChild(innerSurface)
        currentScene.removeChild(outerSurface)
        isShown = false
    } else {
    }

    private fun Model.add(): Unit = if (isShown) {
    } else {
        currentScene.addChild(innerSurface)
        currentScene.addChild(outerSurface)//TODO откалибровать
        outerSurface.getChildAt(2).y -= 0.5//head
        outerSurface.getChildAt(1).y -= 0.3//Body
        outerSurface.getChildAt(3).y -= 0.3//LArm
        outerSurface.getChildAt(4).y -= 0.3//RARm
        outerSurface.getChildAt(5).y -= 0.2//RLeg
        outerSurface.getChildAt(0).y -= 0.2//LLeg
        isShown = true
    }


}