package org.home.kata01.product

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = ['name'], includeFields = true)
class Name {
    private final String name

    static Name of(String name) {
        new Name(name)
    }

    private Name(String name) {
        this.name = name
    }

    @Override
    String toString() {
        name
    }
}