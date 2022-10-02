package com.github.pietroluongo.store

class Controller constructor() {
    private val clothingItems = mutableMapOf<Clothing, Int>();
    private val collectibleItems = mutableMapOf<Collectible, Int>();
    private val electronicItems = mutableMapOf<String, Electronic>();

    fun addClothing(clothing: Clothing) {
        val clothingVal = clothingItems[clothing]
        if(clothingVal == null) {
            clothingItems[clothing] = 1
        }
        else {
            clothingItems[clothing] = clothingVal + 1;
        }
    }

    fun addCollectible(collectible: Collectible) {
        val collectibleVal = collectibleItems[collectible]
        if(collectibleVal == null) {
            collectibleItems[collectible] = 1
        }
        else {
            collectibleItems[collectible] = collectibleVal + 1;
        }
    }

    override fun toString(): String {
        val clothString = clothingItems.entries.joinToString { "${it.key}: ${it.value}" }
        val collectibleString = collectibleItems.entries.joinToString { "${it.key}: ${it.value}" }
        return "[Controller]\n" +
                "CLOTHING:\n" +
                clothString +
                "\n===============================================================================================" +
                "\nCOLLECTIBLES:\n" +
                collectibleString
    }

}