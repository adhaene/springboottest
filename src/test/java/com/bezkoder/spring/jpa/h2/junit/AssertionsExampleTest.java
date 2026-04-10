package com.bezkoder.spring.jpa.h2.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class AssertionsExampleTest {

    @Test
    void testBasicAssertions() {
        // Equality
        assertEquals(5, 2 + 3, "Sum should be 5");
        assertNotEquals(4, 2 + 3);

        // Boolean checks
        assertTrue(5 > 3, "5 should be greater than 3");
        assertFalse(3 > 5);

        // Null checks
        String str = null;
        assertNull(str);
        str = "JUnit";
        assertNotNull(str);

        // Same object reference
        String s1 = "Hello";
        String s2 = s1;
        assertSame(s1, s2);
        assertNotSame(s1, new String("Hello"));

        // Array equality
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray);

        // Iterable equality
        List<String> expectedList = Arrays.asList("A", "B");
        List<String> actualList = Arrays.asList("A", "B");
        assertIterableEquals(expectedList, actualList);
    }

    @Test
    void testExceptionAssertions() {
        // Expect exception
        Exception ex = assertThrows(ArithmeticException.class, () -> {
            int result = 1 / 0;
        });
        assertEquals("/ by zero", ex.getMessage());

        // No exception expected
        assertDoesNotThrow(() -> {
            int result = 1 + 1;
        });
    }
}
