package store
import com.github.pietroluongo.store.Product
import org.junit.jupiter.api.*

class ProductTest {
    @Test
    @DisplayName("Product creation")
    fun testProductCreation(){
        val testUnit = Product("Product Name", 0.0, 0.0, "code")
        Assertions.assertNotNull(testUnit)
    }

}