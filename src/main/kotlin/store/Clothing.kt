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
            return "R-${field.uppercase()}"
        }

    override fun equals(other: Any?): Boolean {
        val conv = other as Clothing;
        return this.productCode == conv.productCode;
    }

    override fun toString(): String {
        return "{\n" +
                "\tname: $name\n" +
                "\tpurchasePrice: $purchasePrice\n" +
                "\tsellingPrice: $sellingPrice\n" +
                "\tproductCode: $productCode\n" +
                "\tsize: $size\n" +
                "\tprimaryColor: $primaryColor\n" +
                "\tsecondaryColor: $secondaryColor\n}"

        return super.toString()
    }
}