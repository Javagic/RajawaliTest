/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 22.08.18 20:08
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

fun Disposable.bind(container: DisposableContainer) = also { container.add(it) }