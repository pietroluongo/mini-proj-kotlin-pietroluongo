package com.github.pietroluongo.store

interface Product {
    val name: String
    val purchasePrice: Double
    var sellingPrice: Double
    val productCode: String
}

