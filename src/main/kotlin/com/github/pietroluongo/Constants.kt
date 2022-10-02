package com.github.pietroluongo

class Constants {
    companion object {
        const val INPUT_FOLDER_INDEX: Int = 0
        const val OUTPUT_FOLDER_INDEX: Int = 1

        const val INPUT_PURCHASES_FILENAME: String = "compras.csv"
        const val INPUT_SALES_FILENAME: String = "vendas.csv"
        const val INPUT_SEARCH_QUERY_FILENAME: String = "busca.csv"

        const val OUTPUT_GENERAL_STOCK_FILENAME: String = "estoque_geral.csv"
        const val OUTPUT_CATEGORIES_STOCK_FILENAME: String = "estoque_categorias.csv"
        const val OUTPUT_BALANCE_FILENAME: String = "balancete.csv"
        const val OUTPUT_SEARCH_RESULT_FILENAME: String = "resultado_busca.csv"

        const val CSV_CODE_COL = 0
        const val CSV_AMOUNT_COL = 1
        const val CSV_NAME_COL = 2
        const val CSV_PURCHASE_PRICE_COL = 3
        const val CSV_SALE_PRICE_COL = 4
        const val CSV_CATEGORY_COL = 5
        const val CSV_TYPE_COL = 6
        const val CSV_SIZE_COL = 7
        const val CSV_PRIMARY_COLOR_COL = 8
        const val CSV_SECONDARY_COLOR_COL = 9
        const val CSV_VERSION_COL = 10
        const val CSV_YEAR_COL = 11
        const val CSV_MATERIAL_COL = 12
        const val CSV_RELEVANCE_COL = 13


        const val CSV_SALE_CODE_COL = 0
        const val CSV_SALE_AMOUNT_COL = 1

        const val CSV_OUTPUT_CODE_COL_NAME = "CODIGO"
        const val CSV_OUTPUT_NAME_COL_NAME = "NOME"
        const val CSV_OUTPUT_AMOUNT_COL_NAME = "QUANTIDADE"
    }

}