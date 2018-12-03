/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 24.08.18 18:51
 */
package com.homeprod.rajawalitest.ext

class Optional<V : Any?>(val value: V? = null)

fun <V : Any?> V.asOptional() = Optional(this)