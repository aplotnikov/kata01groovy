package org.home.kata01.product.amount

import org.home.kata01.utils.TestAmount
import org.junit.Before
import org.junit.Test

import static groovy.test.GroovyAssert.shouldFail

class ImmutableAmountTest {
    private Amount amount

    @Before
    void "prepare test"() throws Exception {
        amount = ImmutableAmount.of(TestAmount.ONE.toInt())
    }

    @Test
    void "exception should be thrown when increase method is executed"() {
        shouldFail(UnsupportedOperationException) {
            amount.increase()
        }
    }

    @Test
    void "exception should be thrown when subtract method is executed"() {
        shouldFail(UnsupportedOperationException) {
            amount.subtract(amount)
        }
    }

    @Test
    void "specialMessageShouldBeReturnFromTOStringMethod"() {
        int amount = TestAmount.ONE.toInt()
        String expectedValue = "Immutable $amount amount"

        assert this.amount.toString() == expectedValue
    }
}