package com.homeprod.rajawalitest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homeprod.rajawalitest.ext.observeOnIo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var page = 1
    private val modelScene by lazy {
        ModelRenderer(this) { handleInitialization() }
    }

    private val disposeOnDestroy = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        surface.setSurfaceRenderer(modelScene)
        next.setOnClickListener {
            LoadImages.getModels(++page)
                .observeOnIo()
                .subscribe{
                    modelScene.setPacks(it)

                }
        }
        prev.setOnClickListener {
            LoadImages.getModels(--page)
                .observeOnIo()
                .subscribe{
                    modelScene.setPacks(it)
                }
        }
    }

    private fun handleInitialization() {
        LoadImages.loadBitmaps()
            .andThen(LoadImages.getModels(page))
            .subscribe {
                modelScene.setPacks(it)
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        disposeOnDestroy.dispose()
    }
}
