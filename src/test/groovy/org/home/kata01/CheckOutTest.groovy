package org.home.kata01

import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.home.kata01.product.Price
import org.home.kata01.product.Product
import org.home.kata01.utils.TestDiscount
import org.home.kata01.utils.TestName
import org.home.kata01.utils.TestPrice
import org.home.kata01.utils.TestProduct
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.home.kata01.CheckOut.Builder.aCheckOut
import static org.home.kata01.product.Product.Builder.aProduct

@RunWith(DataProviderRunner.class)
class CheckOutTest extends GroovyTestCase {
    private CheckOut checkOut

    @Before
    void "prepare test"() {
        Product productA = aProduct().withName(TestName.A.name())
                .withPrice(TestPrice.TEN.getValue())
                .withDiscount(TestDiscount.SECOND.toDiscount())
                .withDiscount(TestDiscount.THIRD.toDiscount())
                .create()

        Product productB = aProduct().withName(TestName.B.name())
                .withPrice(TestPrice.FIVE.getValue())
                .withDiscount(TestDiscount.SECOND.toDiscount())
                .create()

        checkOut = aCheckOut().withProduct(productA)
                .withProduct(productB)
                .withProduct(TestProduct.C.toProduct())
                .create()
    }

    @Test
    void "should be impossible to add two products with the same name"() {
        shouldFail(IllegalStateException) {
            aCheckOut().withProduct(TestProduct.A.toProduct()).withProduct(TestProduct.A.toProduct())
        }
    }

    @DataProvider
    static Object[][] simpleProducts() {
        [
                [TestProduct.A.toProduct()],
                [TestProduct.B.toProduct()],
                [TestProduct.C.toProduct()]
        ] as Object[][]
    }

    @Test
    @UseDataProvider("simpleProducts")
    void "should be able to calculate price for one product"(Product product) {
        checkOut.scan(product.name.toString())

        assert checkOut.getPrice() == product.price
    }

    @DataProvider
    static Object[][] coupleProducts() {
        [
                ["AB", Price.of(15)],
                ["BA", Price.of(15)],
                ["ABC", Price.of(35)]
        ] as Object[][]
    }

    @Test
    @UseDataProvider("coupleProducts")
    void "should be able to calculate price for couple product without repeating name"(String products, Price expectedPrice) {
        "price should be equaled to expected price for given products" products, expectedPrice
    }

    @DataProvider
    static Object[][] coupleProductsWithRepeatingName() {
        [
                ["AAA", Price.of(7)],
                ["AAAA", Price.of(17)],
                ["AAAAA", Price.of(12)],
                ["AAAAAA", Price.of(14)],
                ["AAAAAAA", Price.of(24)],
                ["BB", Price.of(5)],
                ["BBB", Price.of(10)],
                ["AABA", Price.of(12)],
                ["AABAC", Price.of(32)]
        ] as Object[][]
    }

    @Test
    @UseDataProvider("coupleProductsWithRepeatingName")
    void "should be able to calculate price for couple product with repeating name"(String products, Price expectedPrice) {
        "price should be equaled to expected price for given products" products, expectedPrice
    }

    private void "price should be equaled to expected price for given products"(String products, Price expectedPrice) {
        products.split("").each { checkOut.scan(it) }

        assert checkOut.getPrice() == expectedPrice
    }
}