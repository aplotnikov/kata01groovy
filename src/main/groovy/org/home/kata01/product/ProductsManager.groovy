package org.home.kata01.product

class ProductsManager {
    private final Map<Name, Product> products;

    ProductsManager() {
        products = [:]
    }

    void addProduct(Product product) {
        if (products.find { it.key == product.name }) {
            throw new IllegalStateException("The product with \'$product.name\' name is already existed.")
        }

        products.put(product.name, product)
    }

    Product findProductByName(Name name) {
        products.get(name)
    }
}