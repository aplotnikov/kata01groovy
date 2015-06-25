package org.home.kata01.product

import groovy.transform.EqualsAndHashCode
import org.home.kata01.product.amount.Amount

@EqualsAndHashCode
class Price {
    private BigDecimal value;

    static Price zero() {
        of(0)
    }

    static Price of(double value) {
        new Price(value)
    }

    private Price(double value) {
        this.value = BigDecimal.valueOf(value)
    }

    Price add(Price otherPrice) {
        value = value.add(otherPrice.value)
        this
    }

    Price multiply(Amount otherValue) {
        BigDecimal multiplier = BigDecimal.valueOf(otherValue.value())
        value = value.multiply(multiplier)
        this
    }

    @Override
    String toString() {
        value.toString()
    }
}