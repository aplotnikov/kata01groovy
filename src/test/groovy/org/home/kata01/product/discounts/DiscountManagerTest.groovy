package org.home.kata01.product.discounts

import org.home.kata01.product.Price
import org.home.kata01.product.amount.Amount
import org.home.kata01.product.amount.MutableAmount
import org.home.kata01.product.discounts.DiscountManager.IteratorState
import org.home.kata01.utils.TestDiscount
import org.junit.Before
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

class DiscountManagerTest {
    private DiscountManager manager

    @Before
    void setUp() throws Exception {
        manager = new DiscountManager()
    }

    @Test
    void "exception should be thrown when trying to put in duplicate discount"() {
        shouldFail(IllegalStateException) {
            manager.addDiscount(TestDiscount.FIRST.toDiscount())
            manager.addDiscount(TestDiscount.FIRST.toDiscount())
        }
    }

    @Test
    void "iteration process should be performed"() {
        Discount firstDiscount = TestDiscount.FIRST.toDiscount()
        Discount secondDiscount = TestDiscount.SECOND.toDiscount()

        manager.addDiscount(firstDiscount)
        manager.addDiscount(secondDiscount)

        Amount amountOfIteration = MutableAmount.of(0)
        Map<Amount, Price> discounts = [:]

        manager.iterateDiscounts({ Amount productAmountForDiscount, Price discountPrice ->
            amountOfIteration.increase()
            discounts.put(productAmountForDiscount, discountPrice)

            IteratorState.NEXT_ELEMENT
        })

        assert amountOfIteration.value() == 2
        assert discounts.any { it.key == firstDiscount.amount && it.value == firstDiscount.price }
        assert discounts.any { it.key == secondDiscount.amount && it.value == secondDiscount.price }
    }
}