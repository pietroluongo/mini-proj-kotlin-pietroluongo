package store

import org.junit.jupiter.api.*

const val COLLECTIBLE_IDENTIFIER = "Collecto"

class CollectibleTest {
    val collectibleTestUnit = Collectible(
        "Collectible name",
        0.0,
        0.0,
        COLLECTIBLE_IDENTIFIER,
        CollectibleType.Book,
        CollectibleMaterial.Metal,
        4.5,
        CollectibleRelevance.Rare
    )

    @Test
    @DisplayName("Collectible creation")
    fun testCreateClothes() {
        Assertions.assertNotNull(collectibleTestUnit)
    }

    @Test
    @DisplayName("Collectible identifier")
    fun testClothingIdentifier() {
        Assertions.assertEquals("E-COLLECTO", collectibleTestUnit.productCode)
    }

}