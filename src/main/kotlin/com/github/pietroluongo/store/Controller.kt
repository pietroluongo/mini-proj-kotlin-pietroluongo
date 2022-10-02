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
    override fun toString(): String {
        // val clothString = clothingItems.entries.joinToString { "${it.key}: ${it.value}" }
        // val collectibleString = collectibleItems.entries.joinToString { "${it.key}: ${it.value}" }
        val productsString = inventory.entries.joinToString { "${it.key}: ${it.value}" }
        return "[Controller]\n" +
                productsString
    }

}