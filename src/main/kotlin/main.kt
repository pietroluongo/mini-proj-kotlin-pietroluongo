package com.github.pietroluongo

import com.github.pietroluongo.store.Clothing
import com.github.pietroluongo.store.ClothingSizes

const val INPUT_FOLDER_INDEX: Int = 0
const val OUTPUT_FOLDER_INDEX: Int = 1

const val INPUT_PURCHASES_FILENAME: String = "compras.csv"
const val INPUT_SALES_FILENAME: String = "vendas.csv"
const val INPUT_SEARCH_QUERY_FILENAME: String = "busca.csv"

const val OUTPUT_GENERAL_STOCK_FILENAME: String = "estoque_geral.csv"
const val OUTPUT_CATEGORIES_STOCK_FILENAME: String = "estoque_categorias.csv"
const val OUTPUT_BALANCE_FILENAME: String = "balancete.csv"
const val OUTPUT_SEARCH_RESULT_FILENAME: String = "resultado_busca.csv"

fun main(args: Array<String>) {
    var count = 0
    args.forEach {
        println("${count++} $it")
    }
    val clothing = Clothing("product name", 0.0, 0.0, "productCode", ClothingSizes.M, "red", "blue")
}