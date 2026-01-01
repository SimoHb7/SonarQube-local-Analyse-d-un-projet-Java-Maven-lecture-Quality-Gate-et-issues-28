package com.example.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour ValidationUtils
 */
class ValidationUtilsTest {
    
    @Test
    void testIsValidEmail_Valid() {
        assertTrue(ValidationUtils.isValidEmail("test@example.com"));
        assertTrue(ValidationUtils.isValidEmail("user.name@domain.co.uk"));
    }
    
    @Test
    void testIsValidEmail_Invalid() {
        assertFalse(ValidationUtils.isValidEmail(null));
        assertFalse(ValidationUtils.isValidEmail("invalid-email"));
        assertFalse(ValidationUtils.isValidEmail("@example.com"));
    }
    
    @Test
    void testIsValidAge_Valid() {
        assertTrue(ValidationUtils.isValidAge(18));
        assertTrue(ValidationUtils.isValidAge(25));
        assertTrue(ValidationUtils.isValidAge(100));
    }
    
    @Test
    void testIsValidAge_Invalid() {
        assertFalse(ValidationUtils.isValidAge(15));
        assertFalse(ValidationUtils.isValidAge(101));
        assertFalse(ValidationUtils.isValidAge(-1));
    }
    
    @Test
    void testIsValidGpa_Valid() {
        assertTrue(ValidationUtils.isValidGpa(0.0));
        assertTrue(ValidationUtils.isValidGpa(3.5));
        assertTrue(ValidationUtils.isValidGpa(4.0));
    }
    
    @Test
    void testIsValidGpa_Invalid() {
        assertFalse(ValidationUtils.isValidGpa(-0.1));
        assertFalse(ValidationUtils.isValidGpa(4.1));
    }
}
