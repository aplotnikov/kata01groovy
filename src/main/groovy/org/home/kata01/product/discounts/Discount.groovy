package org.home.kata01.product.discounts

import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor
import org.home.kata01.product.Price
import org.home.kata01.product.amount.Amount

import static com.google.common.base.Preconditions.checkState
import static org.home.kata01.product.amount.Amount.Builder.anAmount

@EqualsAndHashCode(includeFields = true, includes = ['amount', 'price'])
@TupleConstructor
class Discount {
    final Amount amount
    final Price price

    @Override
    String toString() {
        int value = amount.value()
        "Discount for $value products with price $price"
    }

    static class Builder {
        private int amount
        private double price

        private Builder() {
        }

        static Builder aDiscount() {
            new Builder()
        }

        Builder forProductAmount(int amount) {
            this.amount = amount
            this
        }

        Builder withPrice(double price) {
            this.price = price
            this
        }

        Discount create() {
            checkState(amount > 0, "'product amount' parameter has to be bigger than zero.")
            checkState(price >= 0, "'price' parameter has to be not less than zero.")

            new Discount(anAmount().withValue(amount).create(), Price.of(price))
        }
    }
}