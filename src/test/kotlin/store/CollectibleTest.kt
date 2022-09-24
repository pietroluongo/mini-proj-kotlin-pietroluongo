package store

import com.github.pietroluongo.store.Collectible
import com.github.pietroluongo.store.CollectibleMaterialType
import com.github.pietroluongo.store.CollectibleRelevance
import com.github.pietroluongo.store.CollectibleType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

const val COLLECTIBLE_IDENTIFIER = "Collecto"

class CollectibleTest {
    private val collectibleTestUnit = Collectible(
        "Collectible name",
        0.0,
        0.0,
        COLLECTIBLE_IDENTIFIER,
        CollectibleType.Toy,
        CollectibleMaterialType.Mixed,
        2.2,
        CollectibleRelevance.Common
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