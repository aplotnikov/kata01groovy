package org.home.kata01.utils

import org.home.kata01.product.Price

enum TestPrice {
    ZERO(0),
    FIVE(5),
    SEVEN(7),
    TEN(10),
    TWENTY(20),
    FIFTY(50)

    private final double value

    TestPrice(double value) {
        this.value = value
    }

    double getValue() {
        value
    }

    Price toPrice() {
        Price.of(value)
    }
}