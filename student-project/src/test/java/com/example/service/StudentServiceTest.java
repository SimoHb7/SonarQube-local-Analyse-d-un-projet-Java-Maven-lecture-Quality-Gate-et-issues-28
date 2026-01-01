package com.example.service;

import com.example.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour StudentService
 * Couverture partielle pour démonstration
 */
class StudentServiceTest {
    
    private StudentService service;
    private Student student1;
    private Student student2;
    
    @BeforeEach
    void setUp() {
        service = new StudentService();
        student1 = new Student("Alice", "Smith", 20, "alice@example.com");
        student1.setGpa(3.7);
        
        student2 = new Student("Bob", "Jones", 22, "bob@example.com");
        student2.setGpa(3.2);
    }
    
    @Test
    void testAddStudent() {
        service.addStudent(student1);
        assertEquals(1, service.getAllStudents().size());
    }
    
    @Test
    void testFindByEmail() {
        service.addStudent(student1);
        Student found = service.findByEmail("alice@example.com");
        assertNotNull(found);
        assertEquals("Alice", found.getFirstName());
    }
    
    @Test
    void testFindByEmail_NotFound() {
        service.addStudent(student1);
        Student found = service.findByEmail("notfound@example.com");
        assertNull(found);
    }
    
    @Test
    void testCalculateAverageGpa() {
        service.addStudent(student1);
        service.addStudent(student2);
        double average = service.calculateAverageGpa();
        assertEquals(3.45, average, 0.01);
    }
    
    @Test
    void testCountExcellentStudents() {
        service.addStudent(student1);
        service.addStudent(student2);
        assertEquals(1, service.countExcellentStudents());
    }
    
    @Test
    void testCountGoodStudents() {
        service.addStudent(student1);
        service.addStudent(student2);
        assertEquals(1, service.countGoodStudents());
    }
    
    @Test
    void testGetAllStudents() {
        service.addStudent(student1);
        service.addStudent(student2);
        assertEquals(2, service.getAllStudents().size());
    }
    
    // Note : plusieurs méthodes ne sont pas testées
    // removeStudentByEmail, importStudents, printAllStudents, etc.
}
