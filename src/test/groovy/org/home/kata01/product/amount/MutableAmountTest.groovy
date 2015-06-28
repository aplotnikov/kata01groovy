package org.home.kata01.product.amount

import junitx.extensions.ComparabilityTestCase
import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.utils.TestAmount
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed.class)
class MutableAmountTest {
    static class GeneralFunctionalityTest {
        @Test
        void "should be zero value"() {
            Amount amount = TestAmount.ZERO.toAmount()
            assert amount.isZero()
        }

        @Test
        void "should be not zero value"() {
            Amount amount = TestAmount.ONE.toAmount()
            assert !amount.isZero()
        }

        @Test
        void "should be one value"() {
            Amount amount = TestAmount.ONE.toAmount()
            assert amount.isOne()
        }

        @Test
        void "should be not one value"() {
            Amount amount = TestAmount.ZERO.toAmount()
            assert !amount.isOne()
        }

        @Test
        void "given amount should be less than current amount"() {
            Amount lessAmount = TestAmount.ZERO.toAmount()
            Amount biggerAmount = TestAmount.ONE.toAmount()

            assert !lessAmount.isBigger(biggerAmount)
        }

        @Test
        void "given amount should be bigger than current amount"() {
            Amount lessAmount = TestAmount.ZERO.toAmount()
            Amount biggerAmount = TestAmount.ONE.toAmount()

            assert biggerAmount.isBigger(lessAmount)
        }

        @Test
        void "value should be subtracted from amount"() {
            Amount amount = TestAmount.FIVE.toAmount()
            Amount subtrahend = TestAmount.ONE.toAmount()

            assert amount.value() == TestAmount.FIVE.toInt()

            amount.subtract(subtrahend)

            assert amount.value() == 4
        }

        @Test
        void "value should be increased"() {
            Amount amount = TestAmount.ZERO.toAmount()

            assert amount.value() == TestAmount.ZERO.toInt()

            amount.increase()

            assert amount.value() == TestAmount.ONE.toInt()
        }

        @Test
        void "special message should be return from to string method"() {
            Amount amount = TestAmount.ONE.toAmount()

            int expectedAmount = TestAmount.ONE.toInt()
            String expectedValue = "Mutable $expectedAmount amount"

            assert amount.toString() == expectedValue
        }
    }

    static class AmountCompatibilityTest extends ComparabilityTestCase {
        AmountCompatibilityTest(String name) {
            super(name)
        }

        @Override
        protected Comparable createLessInstance() throws Exception {
            TestAmount.ZERO.toAmount()
        }

        @Override
        protected Comparable createEqualInstance() throws Exception {
            TestAmount.ONE.toAmount()
        }

        @Override
        protected Comparable createGreaterInstance() throws Exception {
            TestAmount.FIVE.toAmount()
        }
    }

    static class AmountEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        AmountEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            TestAmount.ZERO.toAmount()
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            TestAmount.ONE.toAmount()
        }
    }
}