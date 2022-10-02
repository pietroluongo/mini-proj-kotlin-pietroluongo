package com.github.pietroluongo

import com.github.pietroluongo.Constants.Companion.CSV_AMOUNT_COL
import com.github.pietroluongo.Constants.Companion.CSV_CATEGORY_COL
import com.github.pietroluongo.store.*

import com.github.pietroluongo.Constants.Companion.INPUT_FOLDER_INDEX
import com.github.pietroluongo.Constants.Companion.OUTPUT_FOLDER_INDEX

fun main(args: Array<String>) {
    val inputFolderName = args[INPUT_FOLDER_INDEX]
    val outputFolderName = args[OUTPUT_FOLDER_INDEX]
    try {
        val parser = CSVParser(inputFolderName, outputFolderName)
        val purchases = parser.readPurchases()
        val products: List<Pair<Product?, Int>> = purchases.map {
            val productAmount = it[CSV_AMOUNT_COL].toInt()
            when (it[CSV_CATEGORY_COL]) {
                "ROUPA" -> Pair(Clothing.initFromStringList(it), productAmount)
                "COLECIONAVEL" -> Pair(Collectible("", 0.0, 0.0, "", CollectibleType.Book, CollectibleMaterialType.Mixed, 2.0, CollectibleRelevance.Common), productAmount)
                "ELETRONICO" -> Pair(Electronic.fromStringList(it), productAmount)
                else -> Pair(null, 0)
            }
        }
        val controller: Controller = Controller();
        products.map {
            it.first?.let { it1 -> controller.addProduct(it1, it.second) }
        }
        println(controller)

    }
    catch (e: Exception) {
        println("Failed to open input files")
        return
    }
}