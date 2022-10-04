package com.github.pietroluongo

import com.github.pietroluongo.Constants.Companion.CSV_AMOUNT_COL
import com.github.pietroluongo.Constants.Companion.CSV_CATEGORY_COL
import com.github.pietroluongo.Constants.Companion.CSV_SALE_AMOUNT_COL
import com.github.pietroluongo.Constants.Companion.CSV_SALE_CODE_COL
import com.github.pietroluongo.store.*

import com.github.pietroluongo.Constants.Companion.INPUT_FOLDER_INDEX
import com.github.pietroluongo.Constants.Companion.OUTPUT_FOLDER_INDEX
import java.io.FileNotFoundException
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val inputFolderName = args[INPUT_FOLDER_INDEX]
    val outputFolderName = args[OUTPUT_FOLDER_INDEX]
    try {
        val parser = CSVParser(inputFolderName, outputFolderName)
        val purchases = parser.readPurchases()

        fun createProducts(): List<Pair<Product?, Int>> {
            return purchases.map {
                val productAmount = it[CSV_AMOUNT_COL].toInt()
                when (it[CSV_CATEGORY_COL]) {
                    "ROUPA" -> Pair(Clothing.initFromStringList(it), productAmount)
                    "COLECIONAVEL" -> Pair(Collectible.fromStringList(it), productAmount)
                    "ELETRONICO" -> Pair(Electronic.fromStringList(it), productAmount)
                    else -> Pair(null, 0)
                }
            }
        }

        val products = createProducts()

        val controller: Controller = Controller();
        products.map {
            it.first?.let { it1 -> controller.addProduct(it1, it.second) }
        }

        val sales = parser.readSales()

        sales.forEach {
            controller.handleSale(it[CSV_SALE_CODE_COL], it[CSV_SALE_AMOUNT_COL].toInt())
        }

        val filterStrings = parser.readFilters()

        //println("Filters are: $filters")

        val builtFilters = filterStrings.map { line ->
            line.mapIndexed { index, s ->
                Filter.fromString(s, index)
            }.filterNotNull()
        }.filterNotNull()

        // println(builtFilters)

        parser.writeStock(controller.getStock())
        parser.writeCategoryStock(controller.getStockByCategory())
        val monetaryData = controller.getMonetaryData()
        parser.writeBalance(monetaryData.first, monetaryData.second, monetaryData.third)

        builtFilters.map { filterList ->
            controller.setFilters(filterList)
            println(controller.getFilteredObject())
        }


    } catch (e: NumberFormatException) {
        val stackTrace = e.stackTrace
        println("Failed to parse data.")
        println("Stack trace:")
        stackTrace.map { println(it) }
    } catch (e: FileNotFoundException) {
        println("Failed to open input file.")
        println(e)
        return
    } catch (e: Exception) {
        println("Unknown error: $e")
        return
    }
}