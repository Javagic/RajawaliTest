package com.homeprod.rajawalitest.ext

fun String.correctName() = split(".")[0].replace(Regex("""[ ().]"""), "")
