package org.home.kata01.utils

import org.home.kata01.product.amount.Amount
import org.home.kata01.product.amount.MutableAmount

enum TestAmount {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FIVE(5)

    private final int value

    TestAmount(int value) {
        this.value = value
    }

    Amount toAmount() {
        MutableAmount.of(value)
    }

    int toInt() {
        value
    }
}