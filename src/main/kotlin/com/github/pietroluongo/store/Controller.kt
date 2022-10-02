package com.github.pietroluongo.store

class Controller constructor() {
    private val inventory = mutableMapOf<Product, Int>();

    fun addProduct(prod: Product, amount: Int) {
        val productVal = inventory[prod]

        if(productVal == null) {
            inventory[prod] = amount
        }
        else {
            inventory[prod] = productVal + amount;
        }
    }

    fun handleSale(prodCode: String, amount: Int) {
        val productVal = inventory.entries.find {
            it.component1().productCode == prodCode
        }

        if(productVal == null) {
            println("[WARNING]: Missing product ID $prodCode")
            return
        }
        inventory[productVal.key] = inventory[productVal.key]!!.minus(amount)
    }

    fun getStock(): List<Triple<String, String, Int>> {
        return inventory.entries.map { Triple(it.key.productCode, it.key.name , it.value)}
    }

    override fun toString(): String {
        // val clothString = clothingItems.entries.joinToString { "${it.key}: ${it.value}" }
        // val collectibleString = collectibleItems.entries.joinToString { "${it.key}: ${it.value}" }
        val productsString = inventory.entries.joinToString("\n") { "${it.key}: ${it.value}" }
        return "[Controller]\n" +
                productsString
    }

}