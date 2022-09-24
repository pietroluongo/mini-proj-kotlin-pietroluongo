package store
import org.junit.jupiter.api.*
import com.github.pietroluongo.store.Clothing
import com.github.pietroluongo.store.ClothingSizes

const val ELECTRONIC_IDENTIFIER = "electro"

class ElectronicsTest {
    val electronicTestUnit = Electronic("Electronic name", 0.0, 0.0, ELECTRONIC_IDENTIFIER, ElectronicType.Game, "1.0", 1999)
    @Test
    @DisplayName("Electronic creation")
    fun testCreateClothes() {
        Assertions.assertNotNull(electronicTestUnit)
    }

    @Test
    @DisplayName("Electronic identifier")
    fun testClothingIdentifier() {
        Assertions.assertEquals("E-ELECTRO", electronicTestUnit.productCode)
    }

}