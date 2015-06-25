package org.home.kata01.utils

import org.home.kata01.product.discounts.Discount

import static org.home.kata01.product.discounts.Discount.Builder.aDiscount

enum TestDiscount {
    FIRST(TestAmount.FIVE, TestPrice.TEN),
    SECOND(TestAmount.TWO, TestPrice.FIVE),
    THIRD(TestAmount.THREE, TestPrice.SEVEN)

    private final TestAmount amount
    private final TestPrice price

    TestDiscount(TestAmount amount, TestPrice price) {
        this.amount = amount
        this.price = price
    }

    Discount toDiscount() {
        aDiscount().forProductAmount(amount.toInt())
                .withPrice(price.getValue())
                .create()
    }
}