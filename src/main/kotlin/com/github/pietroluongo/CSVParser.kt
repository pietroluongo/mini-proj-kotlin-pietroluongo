package com.github.pietroluongo

import com.github.pietroluongo.Constants.Companion.INPUT_PURCHASES_FILENAME
import com.github.pietroluongo.Constants.Companion.INPUT_SALES_FILENAME
import java.io.BufferedReader
import java.io.File

class CSVParser constructor(inputFolderName: String, outputFolderName: String) {
    private val inputPurchasesReader: BufferedReader = File("$inputFolderName/$INPUT_PURCHASES_FILENAME").bufferedReader()
    // private val inputSalesReader = File("$inputFolderName/$INPUT_SALES_FILENAME").bufferedReader()

    private fun discardFirstLine(reader: BufferedReader) {
        reader.readLine()
    }
    fun readPurchases(): List<List<String>> {
        discardFirstLine(inputPurchasesReader)
        val lines = inputPurchasesReader.readLines()
        return lines.map { it.uppercase().split(",") }
    }
}