package org.home.kata01.product.amount

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class ImmutableAmount extends MutableAmount {
    static Amount of(int value) {
        new ImmutableAmount(value)
    }

    private ImmutableAmount(int amount) {
        super(amount);
    }

    @Override
    void increase() {
        throw new UnsupportedOperationException()
    }

    @Override
    void subtract(Amount subtrahend) {
        throw new UnsupportedOperationException()
    }

    @Override
    String toString() {
        "Immutable $amount amount"
    }
}