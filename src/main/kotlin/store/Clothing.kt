package com.github.pietroluongo.store

enum class ClothingSizes {
    PP, P, M, G, GG, XG, XXG
}

class Clothing constructor(
    override val name: String,
    override val purchasePrice: Double,
    override var sellingPrice: Double,
    productCode: String,
    val size: ClothingSizes,
    val primaryColor: String,
    val secondaryColor: String? = null
) : Product {
    override val productCode = productCode
        get() {
            return "C-${field.uppercase()}"
        }

}