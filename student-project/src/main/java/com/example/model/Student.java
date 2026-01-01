package com.example.model;

import java.util.Date;

/**
 * Classe représentant un étudiant
 * Cette classe contient intentionnellement des code smells pour la démonstration SonarQube
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private double gpa; // Grade Point Average
    private Date enrollmentDate;
    
    // Variables inutilisées (Code Smell)
    private String unusedField;
    private int anotherUnusedField;
    
    // Constructeur par défaut
    public Student() {
    }
    
    // Constructeur avec paramètres
    public Student(String firstName, String lastName, int age, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.enrollmentDate = new Date();
    }
    
    // Getters et Setters
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public int getAge() {
        return age;
    }
    
    // Bug potentiel : pas de validation de l'âge
    public void setAge(int age) {
        this.age = age; // Devrait vérifier que age > 0
    }
    
    public String getEmail() {
        return email;
    }
    
    // Bug potentiel : pas de validation de l'email
    public void setEmail(String email) {
        this.email = email; // Devrait valider le format email
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Bug potentiel : pas de validation du GPA
    public void setGpa(double gpa) {
        this.gpa = gpa; // Devrait vérifier que gpa est entre 0.0 et 4.0
    }
    
    public Date getEnrollmentDate() {
        return enrollmentDate; // Bug : retourne une référence mutable
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate; // Bug : accepte une référence mutable
    }
    
    /**
     * Méthode avec complexité cyclomatique élevée (Code Smell)
     */
    public String getStudentStatus() {
        String status = "";
        
        if (gpa >= 3.5) {
            if (age < 20) {
                status = "Excellent jeune étudiant";
            } else if (age >= 20 && age < 25) {
                status = "Excellent étudiant";
            } else {
                status = "Excellent étudiant mature";
            }
        } else if (gpa >= 3.0) {
            if (age < 20) {
                status = "Bon jeune étudiant";
            } else if (age >= 20 && age < 25) {
                status = "Bon étudiant";
            } else {
                status = "Bon étudiant mature";
            }
        } else if (gpa >= 2.0) {
            if (age < 20) {
                status = "Étudiant moyen";
            } else {
                status = "Peut mieux faire";
            }
        } else {
            status = "En difficulté";
        }
        
        return status;
    }
    
    /**
     * Méthode avec code dupliqué
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Méthode avec code dupliqué (similaire à getFullName)
     */
    public String getDisplayName() {
        return firstName + " " + lastName; // Duplication
    }
    
    /**
     * Méthode equals mal implémentée (Bug)
     */
    @Override
    public boolean equals(Object obj) {
        // Bug : ne vérifie pas null et ne compare pas les classes
        Student other = (Student) obj; // Peut causer NullPointerException ou ClassCastException
        return this.email.equals(other.email);
    }
    
    // Bug : equals redéfini mais pas hashCode (violation du contrat)
    
    /**
     * Méthode toString mal implémentée (Code Smell)
     */
    @Override
    public String toString() {
        // Utilise la concaténation de String au lieu de StringBuilder
        String result = "Student{";
        result = result + "firstName='" + firstName + "'";
        result = result + ", lastName='" + lastName + "'";
        result = result + ", age=" + age;
        result = result + ", email='" + email + "'";
        result = result + ", gpa=" + gpa;
        result = result + "}";
        return result;
    }
    
    /**
     * Méthode avec gestion d'exception problématique
     */
    public void updateEmail(String newEmail) {
        try {
            // Code susceptible de lever une exception
            this.email = newEmail.toLowerCase();
        } catch (Exception e) {
            // Bad practice : catch trop général et ne fait rien
            // SonarQube détectera ce code smell
        }
    }
    
    /**
     * Méthode avec magic numbers (Code Smell)
     */
    public boolean isEligibleForScholarship() {
        // Magic numbers : 3.5, 25, 2000
        return gpa >= 3.5 && age <= 25;
    }
    
    /**
     * Méthode commentée (Dead Code - Code Smell)
     */
    /*
    public void oldMethod() {
        // Cette méthode n'est plus utilisée mais pas supprimée
        System.out.println("Old code");
    }
    */
}
