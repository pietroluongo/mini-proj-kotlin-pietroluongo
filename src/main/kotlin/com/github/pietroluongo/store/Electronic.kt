package com.github.pietroluongo.store

import com.github.pietroluongo.Constants
import com.github.pietroluongo.Constants.Companion.CSV_CODE_COL
import com.github.pietroluongo.Constants.Companion.CSV_NAME_COL
import com.github.pietroluongo.Constants.Companion.CSV_SIZE_COL
import com.github.pietroluongo.Constants.Companion.CSV_TYPE_COL
import com.github.pietroluongo.Constants.Companion.CSV_VERSION_COL
import com.github.pietroluongo.Constants.Companion.CSV_YEAR_COL

enum class ElectronicType {
    VideoGame,
    Game,
    Portable,
    Other;

    companion object {
        fun fromString(str: String): ElectronicType {
            return when (str) {
                "VIDEO-GAME" -> ElectronicType.VideoGame
                "JOGO" -> ElectronicType.Game
                "PORTATIL" -> ElectronicType.Portable
                "OUTRO" -> ElectronicType.Other
                else -> {
                    println("[WARNING]: Missing Electronic Type string: \"$str\".");
                    return ElectronicType.Other
                }
            }
        }
    }
}

class Electronic constructor(
    override val name: String,
    override val purchasePrice: Double,
    override var sellingPrice: Double,
    productCode: String,
    val type: ElectronicType,
    val version: String,
    val fabricationYear: Number
) : Product {
    override val productCode: String = productCode
        get() {
            return "E-${field.uppercase()}"
        }

    override fun toString(): String {
        return "{\n" +
                "\tname: $name\n" +
                "\tpurchasePrice: $purchasePrice\n" +
                "\tsellingPrice: $sellingPrice\n" +
                "\tproductCode: $productCode\n" +
                "\ttype: $type\n" +
                "\tversion: $version\n" +
                "\tfabricationYear: $fabricationYear\n}"
    }

    companion object {
        fun fromStringList(data: List<String>): Electronic {
            val purchasePrice = data[Constants.CSV_PURCHASE_PRICE_COL].toDouble()
            val salePrice = data[Constants.CSV_SALE_PRICE_COL].toDouble()
            return Electronic(
                data[CSV_NAME_COL],
                purchasePrice,
                salePrice,
                data[CSV_CODE_COL],
                ElectronicType.fromString(data[CSV_TYPE_COL]),
                data[CSV_VERSION_COL],
                data[CSV_YEAR_COL].toInt()
            )
        }
    }
}