package org.home.kata01.product.amount

interface Amount extends Comparable<Amount> {
    int value();

    void increase();

    void subtract(Amount subtrahend);

    boolean isZero();

    boolean isOne();

    boolean isBigger(Amount other);

    static class Builder {
        private boolean isMutable;
        private int value;

        static Builder anAmount() {
            new Builder()
        }

        Builder withValue(int value) {
            this.value = value
            this
        }

        Builder isMutable() {
            isMutable = true
            this
        }

        Amount create() {
            isMutable ? MutableAmount.of(value) : ImmutableAmount.of(value)
        }
    }
}