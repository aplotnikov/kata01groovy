package org.home.kata01.utils

import org.home.kata01.product.Product

import static org.home.kata01.product.Product.Builder.aProduct

enum TestProduct {
    A(TestName.A, TestPrice.TEN),
    B(TestName.B, TestPrice.FIVE),
    C(TestName.C, TestPrice.TWENTY)

    private final TestName name
    private final TestPrice price

    TestProduct(TestName name, TestPrice price) {
        this.name = name
        this.price = price
    }

    Product toProduct() {
        aProduct().withName(name.name())
                .withPrice(price.getValue())
                .create()
    }
}