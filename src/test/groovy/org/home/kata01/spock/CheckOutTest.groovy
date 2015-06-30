package org.home.kata01.spock

import org.home.kata01.CheckOut
import org.home.kata01.product.Price
import org.home.kata01.product.Product
import org.home.kata01.utils.TestDiscount
import org.home.kata01.utils.TestName
import org.home.kata01.utils.TestPrice
import org.home.kata01.utils.TestProduct
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

import static org.home.kata01.CheckOut.Builder.aCheckOut
import static org.home.kata01.product.Product.Builder.aProduct

@Title("Test for checking correct work flow of system")
@Narrative('''As a user I want to scan different kind products and get correct price for scanned products ''')
class CheckOutTest extends Specification {
    // This is just demonstration of annotation
    @Issue("http://my.issues.org/FOO-1")
    @Unroll
    def "scanned '#name' product should cost '#price'"() {
        given: "check out system with pre-configured one product without discounts"
        def checkOut = aCheckOut()
                .withProduct(aProduct().withName(name)
                                       .withPrice(price)
                                       .create())
                .create()

        when: "scan pre-configured product"
        checkOut.scan(name)

        then: "price should be equaled to price of single product"
        checkOut.getPrice() == Price.of(price)

        where:
        name | price
        "A"  | 20
        "B"  | 10
        "C"  | 30
    }

    @Unroll
    def "scanned sequence of products without discounts (#scannedProducts) should cost #price"() {
        given: "check out system with pre-configured a few products without discounts"
        CheckOut checkOut = aCheckOut()
                .withProduct(TestProduct.A.toProduct())
                .withProduct(TestProduct.B.toProduct())
                .withProduct(TestProduct.C.toProduct())
                .create()

        when: "scan every product"
        scannedProducts.split("").each { checkOut.scan(it) }

        then: "price should be equaled to expected price"
        checkOut.getPrice() == Price.of(price)

        where:
        scannedProducts | price
        "AB"            | 15
        "BA"            | 15
        "ABC"           | 35
    }

    @Unroll
    def "scanned sequence of products with discounts (#scannedProducts) should cost #price"() {
        given: "check out system with pre-configured a few products with discounts"
        Product productA = aProduct().withName(TestName.A.name())
                                     .withPrice(TestPrice.TEN.getValue())
                                     .withDiscount(TestDiscount.SECOND.toDiscount())
                                     .withDiscount(TestDiscount.THIRD.toDiscount())
                                     .create()

        Product productB = aProduct().withName(TestName.B.name())
                                     .withPrice(TestPrice.FIVE.getValue())
                                     .withDiscount(TestDiscount.SECOND.toDiscount())
                                     .create()

        CheckOut checkOut = aCheckOut().withProduct(productA)
                                       .withProduct(productB)
                                       .withProduct(TestProduct.C.toProduct())
                                       .create()

        when: "scan every product"
        scannedProducts.split("").each { checkOut.scan(it) }

        then: "price should be equaled to expected price"
        checkOut.getPrice() == Price.of(price)

        where:
        scannedProducts | price
        "AAA"           | 7
        "AAAA"          | 17
        "AAAAA"         | 12
        "AAAAAA"        | 14
        "AAAAAAA"       | 24
        "BB"            | 5
        "BBB"           | 10
        "AABA"          | 12
        "AABAC"         | 32
    }
}