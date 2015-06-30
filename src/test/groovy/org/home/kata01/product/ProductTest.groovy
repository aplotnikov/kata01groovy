package org.home.kata01.product

import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.product.discounts.Discount
import org.home.kata01.utils.TestAmount
import org.home.kata01.utils.TestDiscount
import org.home.kata01.utils.TestName
import org.home.kata01.utils.TestPrice
import org.home.kata01.utils.TestProduct
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

import static groovy.test.GroovyAssert.shouldFail
import static org.home.kata01.product.Product.Builder.aProduct
import static org.home.kata01.product.discounts.Discount.Builder.aDiscount

@RunWith(Enclosed.class)
class ProductTest {
    static class GeneralFunctionalityTest {
        private Product product

        @Before
        void setUp() throws Exception {
            product = aProduct().withName(TestName.A.name())
                                .withPrice(TestPrice.TEN.getValue())
                                .withDiscount(TestDiscount.FIRST.toDiscount())
                                .create()
        }

        @Test
        void "price should be calculated when amount of product is one"() {
            Price price = product.getPriceForAmount(TestAmount.ONE.toAmount())

            assert price == TestPrice.TEN.toPrice()
        }

        @Test
        void "price should be calculated when discount is found"() {
            Price price = product.getPriceForAmount(TestAmount.FIVE.toAmount())

            assert price == TestDiscount.FIRST.toDiscount().price
        }

        @Test
        void "price should be calculated when discount is not found"() {
            Price price = product.getPriceForAmount(TestAmount.TWO.toAmount())

            assert price == TestPrice.TWENTY.toPrice()
        }

        @Test
        void "special message should be return from to string method"() {
            Name name = TestName.A.toName()
            Price price = TestPrice.TEN.toPrice()
            String expectedValue = "\'$name\' product with price $price"

            assert TestProduct.A.toProduct().toString() == expectedValue
        }
    }

    static class ProductEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        ProductEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            TestProduct.A.toProduct()
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            TestProduct.B.toProduct()
        }
    }

    static class BuilderTest {
        @Test
        void "exception should be thrown when name parameter is empty"() {
            shouldFail(IllegalStateException) {
                aProduct().create()
            }
        }

        @Test
        void "exception should be thrown when price parameter is empty"() {
            shouldFail(IllegalStateException) {
                aProduct().withName(TestName.A.name()).create()
            }
        }

        @Test
        void "exception should be thrown when rule with the same amount of product is already existed"() {
            shouldFail(IllegalStateException) {
                Discount discount = aDiscount().forProductAmount(TestAmount.ONE.toInt())
                                               .withPrice(TestPrice.TEN.getValue())
                                               .create()

                aProduct().withDiscount(discount).withDiscount(discount).create()
            }
        }

        @Test
        void "instance should be created"() {
            Product product = TestProduct.A.toProduct()

            assert product
            assert product.name == TestName.A.toName()
            assert product.price == TestPrice.TEN.toPrice()
        }
    }
}