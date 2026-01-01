package com.example.service;

import com.example.model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des étudiants
 * Contient des bugs et code smells pour démonstration SonarQube
 */
public class StudentService {
    
    // Liste non thread-safe (potentiel problème de concurrence)
    private List<Student> students = new ArrayList<>();
    
    // Variable statique mutable (Code Smell)
    public static int totalStudents = 0;
    
    /**
     * Ajoute un étudiant
     * Bug : pas de vérification de null
     */
    public void addStudent(Student student) {
        students.add(student); // Devrait vérifier si student != null
        totalStudents++; // Modification d'une variable statique (code smell)
    }
    
    /**
     * Trouve un étudiant par email
     * Bug potentiel : retourne null au lieu d'Optional
     */
    public Student findByEmail(String email) {
        for (int i = 0; i < students.size(); i++) { // Devrait utiliser for-each
            Student s = students.get(i);
            if (s.getEmail().equals(email)) { // Bug : pas de vérification null
                return s;
            }
        }
        return null; // Code smell : retourne null
    }
    
    /**
     * Supprime un étudiant
     * Bug : modification de la liste pendant l'itération
     */
    public void removeStudentByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                students.remove(student); // ConcurrentModificationException possible
                break;
            }
        }
    }
    
    /**
     * Calcule la moyenne GPA
     * Bug : division par zéro possible
     */
    public double calculateAverageGpa() {
        double sum = 0;
        for (Student student : students) {
            sum += student.getGpa();
        }
        return sum / students.size(); // Bug : si students.size() == 0
    }
    
    /**
     * Obtient tous les étudiants
     * Bug : expose la collection interne
     */
    public List<Student> getAllStudents() {
        return students; // Devrait retourner une copie ou Collections.unmodifiableList
    }
    
    /**
     * Compte les étudiants avec mention
     * Code dupliqué et complexité élevée
     */
    public int countExcellentStudents() {
        int count = 0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getGpa() >= 3.5) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Compte les bons étudiants
     * Duplication de code avec countExcellentStudents
     */
    public int countGoodStudents() {
        int count = 0;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getGpa() >= 3.0 && s.getGpa() < 3.5) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Méthode avec gestion d'exception vide (Bug)
     */
    public void importStudents(String filename) {
        try {
            // Code d'import
            System.out.println("Importing from " + filename);
            // ...
        } catch (Exception e) {
            // Bug : catch vide, erreur silencieuse
        }
    }
    
    /**
     * Méthode avec System.out.println (Code Smell)
     * Devrait utiliser un logger
     */
    public void printAllStudents() {
        System.out.println("Liste des étudiants:"); // Code smell
        for (Student student : students) {
            System.out.println(student); // Code smell
        }
    }
    
    /**
     * Méthode avec trop de paramètres (Code Smell)
     */
    public Student createStudent(String firstName, String lastName, 
                                 int age, String email, double gpa, 
                                 String phone, String address, 
                                 String city, String country) {
        // Trop de paramètres : devrait utiliser un Builder pattern
        Student student = new Student(firstName, lastName, age, email);
        student.setGpa(gpa);
        return student;
    }
    
    /**
     * Vulnérabilité potentielle : SQL Injection si utilisé avec JDBC
     */
    public String buildQuery(String email) {
        // Vulnérabilité : concaténation de string pour SQL
        return "SELECT * FROM students WHERE email = '" + email + "'";
    }
    
    /**
     * Code mort : méthode jamais appelée (Code Smell)
     */
    private void unusedMethod() {
        System.out.println("This method is never called");
    }
}
