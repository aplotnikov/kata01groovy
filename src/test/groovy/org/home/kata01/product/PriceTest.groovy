package org.home.kata01.product

import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.product.amount.Amount
import org.home.kata01.utils.TestAmount
import org.home.kata01.utils.TestPrice
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed.class)
class PriceTest {
    static class GeneralFunctionalityTest {
        @Test
        void "value should be added to price"() {
            Price price = TestPrice.TEN.toPrice()
            Price otherPrice = TestPrice.TEN.toPrice()

            price.add(otherPrice)

            assert price == TestPrice.TWENTY.toPrice()
        }

        @Test
        void "value should be multiplied by value"() {
            Price price = TestPrice.TEN.toPrice()
            Amount multiplier = TestAmount.FIVE.toAmount()

            price.multiply(multiplier)

            assert price == TestPrice.FIFTY.toPrice()
        }

        @Test
        void "instance should be created from double value"() {
            Price price = TestPrice.TEN.toPrice()
            Price actualPrice = TestPrice.TEN.toPrice()

            assert actualPrice == price
        }

        @Test
        void "special message should be return from to string method"() throws Exception {
            String actualValue = TestPrice.TEN.toPrice().toString()
            String expectedValue = BigDecimal.valueOf(TestPrice.TEN.getValue()).toString()

            assert actualValue == expectedValue
        }

        @Test
        void "zero price should be created"() throws Exception {
            assert Price.zero() == TestPrice.ZERO.toPrice()
        }
    }

    static class PriceEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        PriceEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            TestPrice.TEN.toPrice()
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            TestPrice.FIVE.toPrice()
        }
    }
}