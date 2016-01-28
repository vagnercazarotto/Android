package com.example.android.sunshine.app.data;

import android.test.AndroidTestCase;

public class TestPractice extends AndroidTestCase {
    /*
        This gets run before every test.
     */
    ////////////////////////////////////////////
    /// When you extend AndroidTestCase,  you p method which will be run before each test.
    /// You can also implement tearDown which we run after each test. Similar to Junit test,
    /// you simply add new methods in the class with the prefix such as testThatDemostratesAssertions
    /// And those will automatically be run by the test processor.

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testThatDemonstratesAssertions() throws Throwable {
        ////////////////////////
        /// Each test should have at least one check which uses an assert, to see if the program
        /// applies the correct output. An assert is just that, it's a declaration that the outcome after part
        /// of a test is run matches our expectations. If the asset does not match, the test will fail.

        int a = 5;
        int b = 3;
        int c = 5;
        int d = 10;
        //////////////////////
        /// For Example: AssertEquals asserts that the expected value equals the actual value.
        /// In others words A* should be equal to C*, if it's not equal than an assertion failed error is
        /// thrown with the give "X should be equal" message.
        assertEquals("X should be equal", a, c);
        assertTrue("Y should be true", d > a);
        assertFalse("Z should be false", a == b);


        //////////////////
        /// or we can just fail if a certain code path should never have been reached
        if (b > d) {
            fail("XX should never happen");
        }
    }



    /// reference above
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
