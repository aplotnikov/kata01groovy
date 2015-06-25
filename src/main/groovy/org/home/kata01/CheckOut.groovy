package org.home.kata01

import org.home.kata01.product.Price
import org.home.kata01.product.Product
import org.home.kata01.product.ProductsManager
import org.home.kata01.product.amount.Amount
import org.home.kata01.product.scanned.ScannedProductsKeeper

class CheckOut {
    private final ProductsManager productsManager
    private final ScannedProductsKeeper scannedProductsKeeper

    CheckOut(ProductsManager productsManager, ScannedProductsKeeper scannedProductsKeeper) {
        this.productsManager = productsManager
        this.scannedProductsKeeper = scannedProductsKeeper
    }

    void scan(String name) {
        scannedProductsKeeper.addScannedProduct(name)
    }

    Price getPrice() {
        final Price price = Price.zero();
        scannedProductsKeeper.scannedProducts()
                .collect { new Tuple2<Product, Amount>(productsManager.findProductByName(it.name), it.amount) }
                .collect { it.getFirst().getPriceForAmount(it.getSecond()) }
                .each { price.add(it) }
        price
    }

    static class Builder {
        private final ProductsManager productsManager

        private Builder() {
            productsManager = new ProductsManager()
        }

        static Builder aCheckOut() {
            new Builder()
        }

        Builder withProduct(Product product) {
            productsManager.addProduct(product)
            this
        }

        CheckOut create() {
            new CheckOut(productsManager, new ScannedProductsKeeper())
        }
    }
}