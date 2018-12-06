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
import org.rajawali3d.materials.textures.TextureManager
import org.rajawali3d.renderer.Renderer

const val MODEL_DISTANCE_X = 4.0
const val MODEL_LOW_DISTANCE_Y = -8.0
const val MODEL_HIGH_DISTANCE_Y = 8.0
const val MAIN_BACKGROUND_COLOR = 0x2a9955

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
        currentScene.backgroundColor = MAIN_BACKGROUND_COLOR
        currentCamera.z = 40.0
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
        currentScene.addChild(outerSurface)
        isShown = true
    }


}