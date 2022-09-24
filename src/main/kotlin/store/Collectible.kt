package com.github.pietroluongo.store

enum class CollectibleType {
    Book,
    Toy,
    Other
}

enum class CollectibleMaterialType {
    Paper,
    Plastic,
    Steel,
    Mixed,
    Other
}

enum class CollectibleRelevance {
    Common,
    Medium,
    Rare,
    ExtraRare
}

class Collectible constructor(
    override val name: String,
    override var sellingPrice: Double,
    override val purchasePrice: Double,
    productCode: String,
    val type: CollectibleType,
    val material: CollectibleMaterialType,
    val sizeInCm: Double,
    val relevance: CollectibleRelevance
) : Product {
    override val productCode: String = productCode
        get() {
            return "C-${field.uppercase()}"
        }
}