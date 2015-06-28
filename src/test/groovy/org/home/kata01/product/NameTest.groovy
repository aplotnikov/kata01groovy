package org.home.kata01.product

import junitx.extensions.EqualsHashCodeTestCase
import org.home.kata01.utils.TestName
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed.class)
class NameTest {
    static class GeneralFunctionalityTest {
        @Test
        void "special message should be return from to string method"() {
            assert TestName.A.toName().toString() == TestName.A.name()
        }
    }

    static class NameEqualsAndHashCodeTest extends EqualsHashCodeTestCase {
        NameEqualsAndHashCodeTest(String name) {
            super(name)
        }

        @Override
        protected Object createInstance() throws Exception {
            TestName.A.toName()
        }

        @Override
        protected Object createNotEqualInstance() throws Exception {
            TestName.B.toName()
        }
    }
}