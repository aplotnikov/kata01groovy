package org.home.kata01.product

import groovy.transform.EqualsAndHashCode
import org.home.kata01.product.amount.Amount
import org.home.kata01.product.discounts.Discount
import org.home.kata01.product.discounts.DiscountManager

import static com.google.common.base.Preconditions.checkState
import static java.util.Objects.nonNull
import static org.home.kata01.product.amount.Amount.Builder.anAmount
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.NEXT_ELEMENT
import static org.home.kata01.product.discounts.DiscountManager.IteratorState.REPEAT_FOR_CURRENT_ELEMENT

@EqualsAndHashCode(includeFields = true, includes = ['name', 'price'])
class Product {
    final Name name
    final Price price
    private final DiscountManager discountManager

    Product(Name name, Price price, DiscountManager discountManager) {
        this.name = name
        this.price = price
        this.discountManager = discountManager
    }

    Price getPriceForAmount(Amount amount) {
        amount.isOne() ? price : getPriceWithDiscount(amount.value())
    }

    private Price getPriceWithDiscount(int amount) {
        final Price resultPrice = Price.zero()
        final Amount amountOfProduct = anAmount().withValue(amount).isMutable().create()

        discountManager.iterateDiscounts({ Amount productAmountForDiscount, Price discountPrice ->
            if (productAmountForDiscount.isBigger(amountOfProduct)) {
                // it is important to have this 'return' word in other case we'll have never finished loop
                return NEXT_ELEMENT
            }

            amountOfProduct.subtract(productAmountForDiscount)
            resultPrice.add(discountPrice)

            REPEAT_FOR_CURRENT_ELEMENT
        })

        if (!amountOfProduct.isZero()) {
            Price productPrice = price.multiply(amountOfProduct)
            resultPrice.add(productPrice)
        }

        resultPrice
    }

    @Override
    String toString() {
        "\'$name\' product with price $price"
    }

    static class Builder {
        private String name;
        private double price;
        private final DiscountManager discountManager;

        private Builder() {
            discountManager = new DiscountManager()
        }

        static Builder aProduct() {
            new Builder()
        }

        Builder withName(String name) {
            this.name = name
            this
        }

        Builder withPrice(double price) {
            this.price = price
            this
        }

        Builder withDiscount(Discount discount) {
            discountManager.addDiscount(discount)
            this
        }

        Product create() {
            checkState(nonNull(name), "'name' parameter has to be initialized.")
            checkState(price > 0, "'price' parameter has to be not less than zero.")

            new Product(Name.of(name), Price.of(price), discountManager)
        }
    }
}