package org.home.kata01.product.scanned

import groovy.transform.EqualsAndHashCode
import org.home.kata01.product.Name
import org.home.kata01.product.amount.Amount

import static org.home.kata01.product.amount.Amount.Builder.anAmount

@EqualsAndHashCode(includeFields = true, includes = ['name', 'amount'])
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
        def value = amount.value()
        "Product '$name' is scanned $value times"
    }
}