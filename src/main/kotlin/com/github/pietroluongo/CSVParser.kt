package com.github.pietroluongo

import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_AMOUNT_COL_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_BALANCE_IN_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_BALANCE_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_BALANCE_OUT_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_CATEGORY_COL_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_CODE_COL_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_NAME_COL_NAME
import com.github.pietroluongo.Constants.Companion.INPUT_PURCHASES_FILENAME
import com.github.pietroluongo.Constants.Companion.INPUT_SALES_FILENAME
import com.github.pietroluongo.Constants.Companion.INPUT_SEARCH_QUERY_FILENAME
import com.github.pietroluongo.Constants.Companion.OUTPUT_BALANCE_FILENAME
import com.github.pietroluongo.Constants.Companion.OUTPUT_CATEGORIES_STOCK_FILENAME
import com.github.pietroluongo.Constants.Companion.OUTPUT_GENERAL_STOCK_FILENAME
import com.github.pietroluongo.Constants.Companion.OUTPUT_SEARCH_RESULT_FILENAME
import java.io.BufferedReader
import java.io.File
import java.io.FileNotFoundException

class CSVParser constructor(private val inputFolderName: String, private val outputFolderName: String) {
    private val inputPurchasesReader: BufferedReader =
        File("$inputFolderName/$INPUT_PURCHASES_FILENAME").bufferedReader()
    private val inputSalesReader = File("$inputFolderName/$INPUT_SALES_FILENAME").bufferedReader()

    private fun discardFirstLine(reader: BufferedReader) {
        reader.readLine()
    }

    fun readPurchases(): List<List<String>> {
        discardFirstLine(inputPurchasesReader)
        val lines = inputPurchasesReader.readLines()
        inputPurchasesReader.close()
        return lines.map { it.uppercase().split(",") }
    }

    fun readSales(): List<List<String>> {
        discardFirstLine(inputSalesReader)
        val lines = inputSalesReader.readLines()
        inputSalesReader.close()
        return lines.map { it.uppercase().split(",") }
    }

    fun readFilters(): List<List<String>> {
        return try {
            val searchInput = File("$inputFolderName/$INPUT_SEARCH_QUERY_FILENAME").bufferedReader()
            discardFirstLine(searchInput)
            val lines = searchInput.readLines()
            searchInput.close()
            lines.map {it.uppercase().split(",")}

        } catch(e: FileNotFoundException) {
            println("No search file found. Skipping...")
            emptyList()
        }
    }

    fun writeFilterResults(data: List<Int>) {
        val searchOutput = File("$outputFolderName/$OUTPUT_SEARCH_RESULT_FILENAME").bufferedWriter()
        searchOutput.write("BUSCAS,QUANTIDADE\n")
        data.mapIndexed { index, i ->
            searchOutput.write("${index+1},$i\n")
        }
        searchOutput.close()
    }

    fun writeStock(data: List<Triple<String, String, Int>>) {
        val outputStockWriter = File("$outputFolderName/$OUTPUT_GENERAL_STOCK_FILENAME").bufferedWriter()

        fun writeHeader() {
            val headers = listOf(CSV_OUTPUT_CODE_COL_NAME, CSV_OUTPUT_NAME_COL_NAME, CSV_OUTPUT_AMOUNT_COL_NAME)

            headers.map { outputStockWriter.write("$it, ") }
            outputStockWriter.newLine()
        }

        writeHeader()

        fun writeProductData() {
            data.forEach {
                outputStockWriter.write("${it.first}, ${it.second}, ${it.third}")
                outputStockWriter.newLine()
            }
        }

        writeProductData()

        outputStockWriter.close()
    }

    fun writeCategoryStock(data: List<Pair<String, Int>>) {
        val outputCategoryStockWriter = File("$outputFolderName/$OUTPUT_CATEGORIES_STOCK_FILENAME").bufferedWriter()
        fun writeHeader() {
            val headers = listOf(CSV_OUTPUT_CATEGORY_COL_NAME, CSV_OUTPUT_AMOUNT_COL_NAME)

            headers.map { outputCategoryStockWriter.write("$it, ") }
            outputCategoryStockWriter.newLine()
        }

        writeHeader()

        fun writeProductData() {
            data.map {
                outputCategoryStockWriter.write("${it.first}, ${it.second}")
                outputCategoryStockWriter.newLine()
            }
        }
        writeProductData()
        outputCategoryStockWriter.close()
    }

    fun writeBalance(influx: Double, out: Double, net: Double) {
        val outputBalanceWriter = File("$outputFolderName/$OUTPUT_BALANCE_FILENAME").bufferedWriter()

        fun writeDataToFile(colName: String, value: Double) {
            outputBalanceWriter.write("$colName, $value")
            outputBalanceWriter.newLine()
        }

        writeDataToFile(CSV_OUTPUT_BALANCE_IN_NAME, influx)
        writeDataToFile(CSV_OUTPUT_BALANCE_OUT_NAME, out)
        writeDataToFile(CSV_OUTPUT_BALANCE_NAME, net)

        outputBalanceWriter.close()
    }

}