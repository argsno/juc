/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 * Other contributors include Andrew Wright, Jeffrey Hayes,
 * Pat Fisher, Mike Judd.
 */

import junit.framework.Test;
import junit.framework.TestSuite;

public class SystemTest extends JSR166TestCase {
    public static void main(String[] args) {
        main(suite(), args);
    }

    public static Test suite() {
        return new TestSuite(SystemTest.class);
    }

    /**
     * Worst case rounding for millisecs; set for 60 cycle millis clock.
     * This value might need to be changed on JVMs with coarser
     * System.currentTimeMillis clocks.
     */
    static final long MILLIS_ROUND = 17;

    /**
     * Nanos between readings of millis is no longer than millis (plus
     * possible rounding), and vice versa.
     * This shows only that nano timing not (much) worse than milli.
     */
    public void testNanoTime() throws InterruptedException {
        long m0 = System.currentTimeMillis();
        long n0 = System.nanoTime();
        Thread.sleep(1);
        long m1 = System.currentTimeMillis();
        long n1 = System.nanoTime();
        Thread.sleep(50);       // avoid possibly scaled SHORT_DELAY_MS
        long m2 = System.currentTimeMillis();
        long n2 = System.nanoTime();
        Thread.sleep(1);
        long m3 = System.currentTimeMillis();
        long n3 = System.nanoTime();
        assertTrue((n2 - n1) / 1_000_000 <= m3 - m0 + MILLIS_ROUND);
        assertTrue(m2 - m1 <= (n3 - n0) / 1_000_000 + MILLIS_ROUND);
    }
}
