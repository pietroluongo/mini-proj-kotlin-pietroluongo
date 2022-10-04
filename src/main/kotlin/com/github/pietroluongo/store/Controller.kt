package com.github.pietroluongo.store

import java.time.Clock

class Controller constructor() {
    private val inventory = mutableMapOf<Product, Int>();
    private var totalPurchaseValue = 0.0

    private var totalSalesValue = 0.0

    private val filterList: MutableList<Filter> = mutableListOf<Filter>()

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
            Pair("COLECIONAVEL", collectibleStock.sumOf { it.value }),
            Pair("ELETRONICO", electronicStock.sumOf { it.value })
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

    fun setFilters(filters: List<Filter>) {
        filterList.clear()
        filterList.addAll(filters)
    }

    fun getFilteredObject(): List<Pair<Product, Int>> {
        println("Returning data with following filter(s): $filterList\n\n\n")
        val filterResults: List<List<Product>> = filterList.map { filter ->
            when (filter.type) {
                FilterType.Category -> {
                    val filteredItems: List<Product> = when (filter.filterValue) {
                        "ROUPA" -> inventory.filterKeys { it is Clothing }.keys.toList()
                        "ELETRONICO" -> inventory.filterKeys { it is Electronic }.keys.toList()
                        "COLECIONAVEL" -> inventory.filterKeys { it is Collectible }.keys.toList()
                        else -> {
                            println("[WARNING]: Unknown filter category ${filter.filterValue}")
                            emptyList<Product>()
                        }
                    }
                    filteredItems.map { it as Product }
                }

                FilterType.Color -> {
                    val filteredItems = inventory.filterKeys {
                        if (it !is Clothing) {
                            false
                        } else
                            it.primaryColor == filter.filterValue
                    }.keys.toList()
                    filteredItems
                }

                FilterType.Type -> {
                    when (filter.filterValue) {
                        "CAMISA", "MOLETOM", "ACESSORIO" -> {
                            inventory.filterKeys { it is Clothing }
                                .filterKeys { (it as Clothing).type == ClothingType.getFromString(filter.filterValue) }.keys.toList()
                        }

                        "VIDEO-GAME", "JOGO", "PORTATIL" -> {
                            inventory.filterKeys { it is Electronic }
                                .filterKeys { (it as Electronic).type == ElectronicType.fromString(filter.filterValue) }.keys.toList()
                        }

                        "LIVRO", "BONECO" -> {
                            inventory.filterKeys { it is Collectible }
                                .filterKeys { (it as Collectible).type == CollectibleType.fromString(filter.filterValue) }.keys.toList()
                        }

                        "OUTRO" -> {
                            inventory.filterKeys {
                                when(it) {
                                    is Collectible -> it.type == CollectibleType.Other
                                    is Electronic -> it.type == ElectronicType.Other
                                    else -> false

                                }
                            }.keys.toList()
                        }

                        else -> {
                            println("[WARNING]: Missing filter type ${filter.filterValue}")
                            return emptyList()
                        }
                    }
                }

                else -> {
                    println("[WARNING]: Filter type not recognized: ${filter.type}")
                    emptyList<Product>()
                }
            }
        }
        val jointLists = filterResults.reduceRight { products, acc -> acc + products }
        val finalIntersects =
            filterResults.foldRight(jointLists) { products, acc -> acc.intersect(products.toSet()).toList() }.distinct()

        // println("intersections: $finalIntersects")
        return finalIntersects.map {
            Pair(it, inventory[it]!!.or(0))
        }
    }

}