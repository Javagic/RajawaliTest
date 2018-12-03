/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 06.11.18 21:24
 */
package com.homeprod.rajawalitest.ext

fun String?.toLongSafe(defaultValue: Long = -1): Long = try {
  if (this?.isNotBlank() == true) toLong() else defaultValue
} catch (e: NumberFormatException) {
  defaultValue
}