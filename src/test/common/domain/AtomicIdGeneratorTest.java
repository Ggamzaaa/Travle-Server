package test.common.domain;

import common.domain.AtomicIdGenerator;
import common.domain.IdGenerator;
import test.util.TestUtil;

public class AtomicIdGeneratorTest {
    public static void main(String[] args) {
        TestUtil.printHeader("AtomicIdGeneratorTest");

        // seed=0 → 1, 2
        IdGenerator g1 = new AtomicIdGenerator(0);
        int a1 = g1.nextId();
        int a2 = g1.nextId();
        System.out.println("seed=0 → nextId(): " + a1);
        System.out.println("→ nextId(): " + a2);
        TestUtil.assertEquals(1, a1, "first id from seed=0");
        TestUtil.assertEquals(2, a2, "second id from seed=0");

        // seed=5 → 6, 7
        IdGenerator g2 = new AtomicIdGenerator(5);
        int b1 = g2.nextId();
        int b2 = g2.nextId();
        System.out.println("seed=5 → nextId(): " + b1);
        System.out.println("→ nextId(): " + b2);
        TestUtil.assertEquals(6, b1, "first id from seed=5");
        TestUtil.assertEquals(7, b2, "second id from seed=5");

        TestUtil.pass("AtomicIdGeneratorTest");
    }
}
