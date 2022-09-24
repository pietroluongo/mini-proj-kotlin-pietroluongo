package com.github.pietroluongo

import com.github.pietroluongo.store.Clothing
import com.github.pietroluongo.store.ClothingSizes

fun main(args: Array<String>) {
    var count = 0
    args.forEach {
        println("${count++} $it")
    }
    val clothing = Clothing("product name", 0.0, 0.0, "productCode", ClothingSizes.M, "red", "blue")
}