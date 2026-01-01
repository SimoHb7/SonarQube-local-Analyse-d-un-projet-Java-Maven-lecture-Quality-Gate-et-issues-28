package com.example.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 * Tests unitaires pour la classe Student
 * Couverture partielle intentionnelle pour la démonstration
 */
class StudentTest {
    
    private Student student;
    
    @BeforeEach
    void setUp() {
        student = new Student("Jean", "Dupont", 20, "jean.dupont@example.com");
    }
    
    @Test
    void testConstructor() {
        assertNotNull(student);
        assertEquals("Jean", student.getFirstName());
        assertEquals("Dupont", student.getLastName());
        assertEquals(20, student.getAge());
        assertEquals("jean.dupont@example.com", student.getEmail());
    }
    
    @Test
    void testGettersAndSetters() {
        student.setFirstName("Marie");
        assertEquals("Marie", student.getFirstName());
        
        student.setLastName("Martin");
        assertEquals("Martin", student.getLastName());
        
        student.setAge(22);
        assertEquals(22, student.getAge());
        
        student.setEmail("marie.martin@example.com");
        assertEquals("marie.martin@example.com", student.getEmail());
    }
    
    @Test
    void testGpa() {
        student.setGpa(3.5);
        assertEquals(3.5, student.getGpa());
    }
    
    @Test
    void testGetFullName() {
        assertEquals("Jean Dupont", student.getFullName());
    }
    
    @Test
    void testGetDisplayName() {
        assertEquals("Jean Dupont", student.getDisplayName());
    }
    
    @Test
    void testGetStudentStatus_Excellent() {
        student.setGpa(3.7);
        student.setAge(19);
        String status = student.getStudentStatus();
        assertTrue(status.contains("Excellent"));
    }
    
    @Test
    void testGetStudentStatus_Good() {
        student.setGpa(3.2);
        student.setAge(21);
        String status = student.getStudentStatus();
        assertTrue(status.contains("Bon"));
    }
    
    @Test
    void testIsEligibleForScholarship_Eligible() {
        student.setGpa(3.7);
        student.setAge(22);
        assertTrue(student.isEligibleForScholarship());
    }
    
    @Test
    void testIsEligibleForScholarship_NotEligible() {
        student.setGpa(2.5);
        student.setAge(22);
        assertFalse(student.isEligibleForScholarship());
    }
    
    @Test
    void testUpdateEmail() {
        student.updateEmail("NEWEMAIL@EXAMPLE.COM");
        assertEquals("newemail@example.com", student.getEmail());
    }
    
    @Test
    void testToString() {
        student.setGpa(3.5);
        String result = student.toString();
        assertTrue(result.contains("Jean"));
        assertTrue(result.contains("Dupont"));
    }
    
    // Note : certaines méthodes ne sont pas testées (couverture partielle)
    // Par exemple : equals, hashCode, getEnrollmentDate
}
