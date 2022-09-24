package com.github.pietroluongo.store

class Clothing constructor(override val name: String, override val purchasePrice: Double, override var sellingPrice: Double, productCode: String) : Product {
    override val productCode = productCode
        get() {
            return "C-${field.uppercase()}"
        }

}