package org.home.kata01.product.scanned

import org.home.kata01.utils.TestProduct
import org.junit.Test

class ScannedProductsKeeperTest {
    @Test
    void "should be able to iterate different items"() {
        ScannedProductsKeeper keeper = new ScannedProductsKeeper()

        String firstProductName = TestProduct.A.toProduct().name.toString()
        String secondProductName = TestProduct.B.toProduct().name.toString()

        keeper.addScannedProduct(firstProductName)
        keeper.addScannedProduct(secondProductName)
        keeper.addScannedProduct(firstProductName)

        ScannedProduct scannedProductA = ScannedProduct.of(firstProductName);
        scannedProductA.increaseAmount();
        ScannedProduct scannedProductB = ScannedProduct.of(secondProductName);

        def scannedProducts = keeper.scannedProducts()
        assert scannedProducts.any { it == scannedProductA }
        assert scannedProducts.any { it == scannedProductB }
        assert scannedProducts.size() == 2
    }
}