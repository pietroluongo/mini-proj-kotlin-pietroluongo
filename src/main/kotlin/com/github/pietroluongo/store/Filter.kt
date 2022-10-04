package com.github.pietroluongo.store

import com.github.pietroluongo.Constants.Companion.CSV_QUERY_CATEGORY_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_COLOR2_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_COLOR_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_MATERIAL_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_RELEVANCE_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_SIZE_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_TYPE_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_VERSION_COL
import com.github.pietroluongo.Constants.Companion.CSV_QUERY_YEAR_COL

enum class FilterType {
    Category,
    Type,
    Size,
    Color,
    SecondColor,
    Version,
    Year,
    Material,
    Relevance,
    None;

    companion object {
        fun fromColumnNumber(num: Int): FilterType {
            return when (num) {
                CSV_QUERY_CATEGORY_COL -> Category
                CSV_QUERY_TYPE_COL -> Type
                CSV_QUERY_SIZE_COL -> Size
                CSV_QUERY_COLOR_COL -> Color
                CSV_QUERY_COLOR2_COL -> SecondColor
                CSV_QUERY_VERSION_COL -> Version
                CSV_QUERY_YEAR_COL -> Year
                CSV_QUERY_MATERIAL_COL -> Material
                CSV_QUERY_RELEVANCE_COL -> Relevance
                else -> {
                    println("[WARNING]: Failed to match filter column - '$num'")
                    return None
                }
            }
        }

    }
}

class Filter constructor(val type: FilterType, val filterValue: String) {
    override fun toString(): String {
        return "\n[Filter]" +
                "\n{\n\tType: $type" +
                "\n\tValue: $filterValue" +
                "\n}"

    }

    companion object {
        fun fromString(str: String, columnId: Int): Filter? {
            if (str == "-") return null
            return Filter(FilterType.fromColumnNumber(columnId), str)
        }
        //fun fromStringList(strs: List<String>): List<Filter> {
        //    strs.map {
        //        return fromString(it, 0)
        //    }
        //}

    }
}