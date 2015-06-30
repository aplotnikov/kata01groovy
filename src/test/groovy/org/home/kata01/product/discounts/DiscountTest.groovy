package org.home.kata01.product.discounts

import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.product.Price
import org.home.kata01.product.amount.Amount
import org.home.kata01.utils.TestAmount
import org.home.kata01.utils.TestDiscount
import org.home.kata01.utils.TestPrice
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

import static groovy.test.GroovyAssert.shouldFail
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount

@RunWith(Enclosed.class)
class DiscountTest {
    static class GeneralFunctionalityTest {
        @Test
        void "special message should be return from to string method"() {
            def productAmount = TestAmount.FIVE.toInt()
            def productPrice = TestPrice.TEN.toPrice()

            String expectedValue = "Discount for $productAmount products with price $productPrice"
            assert TestDiscount.FIRST.toDiscount().toString() == expectedValue
        }
    }

    static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        AmountEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            TestDiscount.FIRST.toDiscount()
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            TestDiscount.SECOND.toDiscount()
        }
    }

    static class BuilderTest {
        // IMPORTANT. Name of test methods have to have prefix 'test' in other case this method won't launch. It isn't important to use @Test annotation.
        private static final int INCORRECT_VALUE = -1

        @Test
        void "exception should be thrown when amount parameter is less than zero"() {
            shouldFail(IllegalStateException) {
                aDiscount().forProductAmount(INCORRECT_VALUE).create()
            }
        }

        @Test
        void "exception should be thrown when price parameter is empty"() {
            shouldFail(IllegalStateException) {
                aDiscount().withPrice(TestPrice.TEN.getValue()).create()
            }
        }

        @Test
        void "new instance of disocunt should be created by builder"() {
            Amount expectedAmount = TestAmount.FIVE.toAmount()
            Price expectedPrice = TestPrice.TEN.toPrice()

            Discount product = aDiscount().forProductAmount(TestAmount.FIVE.toInt())
                                          .withPrice(TestPrice.TEN.getValue())
                                          .create()

            assert product
            assert product.amount == expectedAmount
            assert product.price == expectedPrice
        }
    }
}