package org.home.kata01.product.discounts

import org.home.kata01.product.Price
import org.home.kata01.product.amount.Amount

import java.util.stream.Collectors

class DiscountManager {
    private final Map<Amount, Discount> rules;

    DiscountManager() {
        rules = [:]
    }

    void addDiscount(Discount discount) throws IllegalStateException {
        if (rules.containsKey(discount.amount)) {
            throw new IllegalStateException("The discount for a given amount of product is already existed.")
        }

        rules.put(discount.amount, discount)
    }

    void iterateDiscounts(Closure discountAnalyzer) {
        List<Amount> amounts = getSortedDiscounts()

        int ruleIndex = 0;
        int discountsAmount = amounts.size();

        while (ruleIndex < discountsAmount) {
            Amount ruleAmount = amounts.get(ruleIndex);
            Price price = rules.get(ruleAmount).price;

            IteratorState state = discountAnalyzer(ruleAmount, price);

            if (IteratorState.NEXT_ELEMENT.equals(state)) {
                ruleIndex++;
            }
        }
    }

    List<Amount> getSortedDiscounts() {
        rules.keySet().stream().sorted(Comparator.<Amount> reverseOrder()).collect(Collectors.toList())
    }

    enum IteratorState {
        NEXT_ELEMENT, REPEAT_FOR_CURRENT_ELEMENT
    }
}