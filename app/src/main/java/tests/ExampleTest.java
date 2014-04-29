package tests;

import android.test.InstrumentationTestCase;

/**
 * Created by emonk on 4/28/14.
 */
public class ExampleTest extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}

