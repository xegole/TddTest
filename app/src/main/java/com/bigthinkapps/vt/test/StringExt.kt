package com.bigthinkapps.vt.test

fun String.inputValue(): Double {
    return if (this.isEmpty()) 0.0 else this.toDouble()
}