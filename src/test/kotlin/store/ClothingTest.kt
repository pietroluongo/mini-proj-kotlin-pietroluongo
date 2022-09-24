package store

import com.github.pietroluongo.store.Clothing
import com.github.pietroluongo.store.ClothingSizes
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

const val CLOTHING_IDENTIFIER = "cloth"

class ClothingTest {
    private val clothing = Clothing("Clothing name", 0.0, 0.0, CLOTHING_IDENTIFIER, ClothingSizes.M, "red", "blue")
    private val clothingWithoutSecondaryColor =
        Clothing("Clothing name", 0.0, 0.0, "$CLOTHING_IDENTIFIER-NOCOLOR", ClothingSizes.M, "red")

    @Test
    @DisplayName("Clothes creation")
    fun testCreateClothes() {
        Assertions.assertNotNull(clothing)
        Assertions.assertNotNull(clothingWithoutSecondaryColor)
    }

    @Test
    @DisplayName("Clothes identifier")
    fun testClothingIdentifier() {
        Assertions.assertEquals("C-CLOTH", clothing.productCode)
    }

}