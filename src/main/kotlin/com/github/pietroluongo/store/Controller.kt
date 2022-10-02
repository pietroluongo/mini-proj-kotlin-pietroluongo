package com.github.pietroluongo.store

class Controller constructor() {
    private val inventory = mutableMapOf<Product, Int>();
    private var totalPurchaseValue = 0.0

    private var totalSalesValue = 0.0

    fun addProduct(prod: Product, amount: Int) {
        val productVal = inventory[prod]
        totalPurchaseValue += prod.purchasePrice * amount

        if (productVal == null) {
            inventory[prod] = amount
        } else {
            inventory[prod] = productVal + amount;
        }
    }

    fun handleSale(prodCode: String, amount: Int) {
        val productVal = inventory.entries.find {
            it.component1().productCode == prodCode
        }

        if (productVal == null) {
            println("[WARNING]: Missing product ID $prodCode")
            return
        }
        totalSalesValue += productVal.key.sellingPrice * amount
        inventory[productVal.key] = inventory[productVal.key]!!.minus(amount)
    }

    fun getStock(): List<Triple<String, String, Int>> {
        return inventory.entries.map { Triple(it.key.productCode, it.key.name, it.value) }
    }

    fun getStockByCategory(): List<Pair<String, Int>> {
        val clothingStock = inventory.entries.filter { it.key is Clothing }
        val collectibleStock = inventory.entries.filter { it.key is Collectible }
        val electronicStock = inventory.entries.filter { it.key is Electronic }
        return listOf(
            Pair("ROUPA", clothingStock.sumOf { it.value }),
            Pair("COLECIONAVEL", collectibleStock.sumOf {it.value}),
            Pair("ELETRONICO", electronicStock.sumOf {it.value})
        )
    }

    override fun toString(): String {
        // val clothString = clothingItems.entries.joinToString { "${it.key}: ${it.value}" }
        // val collectibleString = collectibleItems.entries.joinToString { "${it.key}: ${it.value}" }
        val productsString = inventory.entries.joinToString("\n") { "${it.key}: ${it.value}" }
        return "[Controller]\n" +
                productsString +
                "\nCash flow: in ${totalPurchaseValue}, out ${totalSalesValue}, net ${totalSalesValue - totalPurchaseValue}"
    }

    fun getMonetaryData(): Triple<Double, Double, Double> {
        return Triple(totalPurchaseValue, totalSalesValue, totalSalesValue - totalPurchaseValue)
    }

}