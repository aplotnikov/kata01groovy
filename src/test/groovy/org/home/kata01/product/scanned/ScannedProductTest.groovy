package org.home.kata01.product.scanned

import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.utils.TestName
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed.class)
class ScannedProductTest {
    static class GeneralFunctionalityTest {
        @Test
        void "special message should be return from to string method"() {
            ScannedProduct scannedProduct = ScannedProduct.of(TestName.A.name())
            String name = TestName.A.name()

            String expectedValue = "Product \'$name\' is scanned 1 times"

            assert scannedProduct.toString() == expectedValue
        }
    }

    static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        AmountEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            ScannedProduct.of(TestName.A.name())
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            ScannedProduct scannedProduct = ScannedProduct.of(TestName.A.name())
            scannedProduct.increaseAmount()

            scannedProduct
        }
    }
}