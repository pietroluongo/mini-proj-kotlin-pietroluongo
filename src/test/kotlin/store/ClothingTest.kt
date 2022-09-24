package store
import org.junit.jupiter.api.*
import com.github.pietroluongo.store.Clothing
import com.github.pietroluongo.store.ClothingSizes

const val CLOTHING_IDENTIFIER = "cloth"

class ClothingTest {
    val clothing = Clothing("Clothing name", 0.0, 0.0, CLOTHING_IDENTIFIER, ClothingSizes.M, "red", "blue");
    val clothingWithoutSecondaryColor = Clothing("Clothing name", 0.0, 0.0, "$CLOTHING_IDENTIFIER-NOCOLOR", ClothingSizes.M, "red");
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