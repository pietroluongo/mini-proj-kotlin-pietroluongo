package com.github.pietroluongo.store

import com.github.pietroluongo.Constants
import com.github.pietroluongo.Constants.Companion.CSV_CODE_COL
import com.github.pietroluongo.Constants.Companion.CSV_MATERIAL_COL
import com.github.pietroluongo.Constants.Companion.CSV_NAME_COL
import com.github.pietroluongo.Constants.Companion.CSV_RELEVANCE_COL
import com.github.pietroluongo.Constants.Companion.CSV_SIZE_COL
import com.github.pietroluongo.Constants.Companion.CSV_TYPE_COL

enum class CollectibleType {
    Book,
    Toy,
    Other;

    companion object {
        fun fromString(str: String): CollectibleType {
            return when (str) {
                "LIVRO" -> Book
                "BONECO" -> Toy
                "OUTROS" -> Other
                else -> {
                    println("[WARNING]: Missing Collectible type string: $str")
                    Other
                }
            }
        }
    }

}

enum class CollectibleMaterialType {
    Paper,
    Plastic,
    Steel,
    Mixed,
    Other;

    companion object {
        fun fromString(str: String): CollectibleMaterialType {
            return when (str) {
                "PAPEL" -> Paper
                "PLASTICO" -> Plastic
                "ACO" -> Steel
                "MISTURADO" -> Mixed
                "OUTROS" -> Other
                else -> {
                    println("[WARNING]: Missing CollectibleMaterialType string: $str")
                    Other
                }
            }
        }
    }
}

enum class CollectibleRelevance {
    Common,
    Medium,
    Rare,
    ExtraRare;

    companion object {
        fun fromString(str: String): CollectibleRelevance {
            return when (str) {
                "COMUM" -> Common
                "MEDIO" -> Medium
                "RARO" -> Rare
                "RARISSIMO" -> ExtraRare
                else -> {
                    println("[WARNING]: Missing CollectibleRelevance string.")
                    Common
                }
            }
        }
    }
}

class Collectible constructor(
    override val name: String,
    override val purchasePrice: Double,
    override var sellingPrice: Double,
    productCode: String,
    val type: CollectibleType,
    val material: CollectibleMaterialType,
    val sizeInCm: Double?,
    val relevance: CollectibleRelevance
) : Product {
    override val productCode: String = productCode
        get() {
            return "C-${field.uppercase()}"
        }

    override fun equals(other: Any?): Boolean {
        return this.productCode == (other as Product).productCode;
    }

    override fun toString(): String {
        return "{\n" +
                "\tname: $name\n" +
                "\tsellingPrice: $sellingPrice\n" +
                "\tpurchasePrice: $purchasePrice\n" +
                "\tproductCode: $productCode\n" +
                "}"
    }

    companion object {
        fun fromStringList(data: List<String>): Collectible {
            val purchasePrice = data[Constants.CSV_PURCHASE_PRICE_COL].toDouble()
            val salePrice = data[Constants.CSV_SALE_PRICE_COL].toDouble()
            return Collectible(
                data[CSV_NAME_COL],
                purchasePrice,
                salePrice,
                data[CSV_CODE_COL],
                CollectibleType.fromString(data[CSV_TYPE_COL]),
                CollectibleMaterialType.fromString(data[CSV_MATERIAL_COL]),
                data[CSV_SIZE_COL].toDoubleOrNull(),
                CollectibleRelevance.fromString(data[CSV_RELEVANCE_COL])
            )
        }
    }
}