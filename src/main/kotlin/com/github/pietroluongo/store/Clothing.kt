package com.github.pietroluongo.store

import com.github.pietroluongo.Constants.Companion.CSV_CODE_COL
import com.github.pietroluongo.Constants.Companion.CSV_NAME_COL
import com.github.pietroluongo.Constants.Companion.CSV_PRIMARY_COLOR_COL
import com.github.pietroluongo.Constants.Companion.CSV_PURCHASE_PRICE_COL
import com.github.pietroluongo.Constants.Companion.CSV_SALE_PRICE_COL
import com.github.pietroluongo.Constants.Companion.CSV_SECONDARY_COLOR_COL
import com.github.pietroluongo.Constants.Companion.CSV_SIZE_COL

enum class ClothingSizes {
    PP, P, M, G, GG, XG, XXG;

    companion object {
        fun getSizeFromString(sz: String): ClothingSizes {
            return when (sz) {
                "PP" -> ClothingSizes.PP
                "P" -> ClothingSizes.P
                "M" -> ClothingSizes.M
                "G" -> ClothingSizes.G
                "GG" -> ClothingSizes.GG
                "XG" -> ClothingSizes.XG
                "XXG" -> ClothingSizes.XXG
                else -> ClothingSizes.PP
            }
        }
    }
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
    }

    companion object {
        fun initFromStringList(data: List<String>): Clothing {
            val purchasePrice = data[CSV_PURCHASE_PRICE_COL].toDouble()
            val salePrice = data[CSV_SALE_PRICE_COL].toDouble()
            val secondaryColor = if (data[CSV_SECONDARY_COLOR_COL] == "-") null else data[CSV_SECONDARY_COLOR_COL]
            return Clothing(
                data[CSV_NAME_COL],
                purchasePrice,
                salePrice,
                data[CSV_CODE_COL],
                ClothingSizes.getSizeFromString(data[CSV_SIZE_COL]),
                data[CSV_PRIMARY_COLOR_COL],
                secondaryColor
            )
        }
    }
}