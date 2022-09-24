package com.github.pietroluongo.store

class Collectible constructor(
    override val name: String,
    override var sellingPrice: Double,
    override val purchasePrice: Double,
    productCode: String
) : Product {
    override val productCode: String
        get() {
            return "C-${productCode.uppercase()}"
        }
}