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

const val ZERO = 0.0

class ModelRenderer(context: Context, private val action: () -> Unit) : Renderer(context, true) {

    private val previewModel by lazy {
        createModel(4, ZERO, ZERO)
    }
    private val obj by lazy {
        LoaderOBJ(context.resources, TextureManager.getInstance(), R.raw.steve_obj)
            .parse()
            .parsedObject
    }

    override fun onRender(ellapsedRealtime: Long, deltaTime: Double) {
        super.onRender(ellapsedRealtime, deltaTime)
        previewModel.render(deltaTime)
    }

    private fun createModel(position: Int, x: Double, y: Double): Model {
        return Model(position).apply {
            innerSurface = obj.clone(false, true)
            outerSurface = obj.clone(false, true)
            build(x, y)
        }

    }

    override fun onTouchEvent(event: MotionEvent?) {}

    override fun initScene() {
        action()

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

}