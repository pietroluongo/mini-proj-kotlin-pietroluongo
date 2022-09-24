package com.github.pietroluongo.store

enum class ElectronicType {
    VideoGame,
    Game,
    Portable,
    Other
}

class Electronic constructor(
    override val name: String,
    override var sellingPrice: Double,
    override val purchasePrice: Double,
    productCode: String,
    val type: ElectronicType,
    val version: String,
    val fabricationYear: Number
) : Product {
    override val productCode: String = productCode
        get() {
            return "E-${field.uppercase()}"
        }
}