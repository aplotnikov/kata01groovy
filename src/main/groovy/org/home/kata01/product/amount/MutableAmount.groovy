package org.home.kata01.product.amount

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includeFields = true, includes = ['amount'])
class MutableAmount implements Amount {
    protected int amount

    static Amount of(int value) {
        new MutableAmount(value)
    }

    protected MutableAmount(int amount) {
        this.amount = amount
    }

    @Override
    int value() {
        amount
    }

    @Override
    int compareTo(Amount other) {
        Integer.compare(value(), other.value())
    }

    @Override
    boolean isZero() {
        amount == 0
    }

    @Override
    boolean isOne() {
        amount == 1
    }

    @Override
    boolean isBigger(Amount other) {
        value() > other.value()
    }

    @Override
    void subtract(Amount subtrahend) {
        amount -= subtrahend.value()
    }

    @Override
    void increase() {
        amount++
    }

    @Override
    String toString() {
        "Mutable $amount amount"
    }
}