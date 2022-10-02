package com.github.pietroluongo

import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_AMOUNT_COL_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_CODE_COL_NAME
import com.github.pietroluongo.Constants.Companion.CSV_OUTPUT_NAME_COL_NAME
import com.github.pietroluongo.Constants.Companion.INPUT_PURCHASES_FILENAME
import com.github.pietroluongo.Constants.Companion.INPUT_SALES_FILENAME
import com.github.pietroluongo.Constants.Companion.OUTPUT_GENERAL_STOCK_FILENAME
import java.io.BufferedReader
import java.io.File

class CSVParser constructor(inputFolderName: String, private val outputFolderName: String) {
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

}