package org.home.kata01.utils

import org.home.kata01.product.Name

enum TestName {
    A,
    B,
    C

    Name toName() {
        Name.of(name())
    }
}