package org.home.kata01.product.scanned

import groovy.transform.EqualsAndHashCode
import org.home.kata01.product.Name
import org.home.kata01.product.amount.Amount

import static org.home.kata01.product.amount.Amount.Builder.anAmount

@EqualsAndHashCode
class ScannedProduct {
    final Name name
    final Amount amount

    static ScannedProduct of(String name) {
        new ScannedProduct(name)
    }

    private ScannedProduct(String name) {
        this.name = Name.of(name)
        amount = anAmount().withValue(1).isMutable().create()
    }

    void increaseAmount() {
        amount.increase()
    }

    @Override
    String toString() {
        "Product '$name' is scanned $amount.value() times"
    }
}