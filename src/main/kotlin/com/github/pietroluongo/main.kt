package com.github.pietroluongo

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
        val products: List<Product?> = purchases.map {
            when (it[CSV_CATEGORY_COL]) {
                "ROUPA" -> Clothing.initFromStringList(it)
                "COLECIONAVEL" -> Collectible("", 0.0, 0.0, "", CollectibleType.Book, CollectibleMaterialType.Mixed, 2.0, CollectibleRelevance.Common)
                "ELETRONICO" -> Electronic("", 0.0, 0.0, "", ElectronicType.Game, "", 1234)
                else -> null
            }
        }
        println(products)
    }
    catch (e: Exception) {
        println("Failed to open input files")
        return
    }


    val controller: Controller = Controller();
    val clothing = Clothing("product name", 0.0, 0.0, "productCode", ClothingSizes.M, "red", "blue")
    val collectible = Collectible("collectible name", 0.0, 0.0, "collectibleCode", CollectibleType.Book, CollectibleMaterialType.Mixed, 2.0, CollectibleRelevance.Common)
    controller.addProduct(clothing, 1)
    controller.addProduct(collectible,1 )
    controller.addProduct(clothing, 1)
    controller.addProduct(clothing, 1)
    println(controller)
}