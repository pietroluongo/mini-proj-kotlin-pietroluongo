package store
import org.junit.jupiter.api.*
import com.github.pietroluongo.store.Clothing

const val CLOTHING_IDENTIFIER = "cloth"

class ClothingTest {
    val clothing = Clothing("Clothing name", 0.0, 0.0, CLOTHING_IDENTIFIER, ClothingType.M, "red", "blue");
    val clothingWithoutSecondaryColor = Clothing("Clothing name", 0.0, 0.0, "$CLOTHING_IDENTIFIER-NOCOLOR", ClothingType.M, "red");
    @Test
    @DisplayName("Clothes creation")
    fun testCreateClothes() {
        Assertions.assertNotNull(clothing)
    }

    @Test
    @DisplayName("Clothes identifier")
    fun testClothingIdentifier() {
        Assertions.assertEquals("C-cloth", clothing.name)
    }

}