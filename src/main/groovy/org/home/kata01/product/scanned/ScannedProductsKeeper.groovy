package org.home.kata01.product.scanned

import org.home.kata01.product.Name

class ScannedProductsKeeper {
    private final Map<Name, ScannedProduct> scannedProducts

    ScannedProductsKeeper() {
        scannedProducts = [:]
    }

    void addScannedProduct(String name) {
        Name productName = Name.of(name)
        ScannedProduct scannedProduct = scannedProducts.get(productName)

        if (scannedProduct) {
            scannedProduct.increaseAmount()
        } else {
            scannedProducts.put(productName, ScannedProduct.of(name))
        }
    }

    Collection<ScannedProduct> scannedProducts() {
        scannedProducts.values()
    }
}