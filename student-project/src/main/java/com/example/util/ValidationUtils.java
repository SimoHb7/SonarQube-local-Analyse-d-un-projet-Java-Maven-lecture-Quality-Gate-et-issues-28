package com.example.util;

import java.util.regex.Pattern;

/**
 * Classe utilitaire de validation
 * Contient quelques code smells pour la démonstration
 */
public class ValidationUtils {
    
    // Pattern compilé à chaque fois (Code Smell)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    
    /**
     * Valide un email
     * Code smell : Pattern recompilé à chaque appel
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX); // Devrait être statique
        return pattern.matcher(email).matches();
    }
    
    /**
     * Valide un âge
     */
    public static boolean isValidAge(int age) {
        return age >= 16 && age <= 100; // Magic numbers
    }
    
    /**
     * Valide un GPA
     */
    public static boolean isValidGpa(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }
    
    /**
     * Constructeur privé pour classe utilitaire
     * Bonne pratique présente dans ce cas
     */
    private ValidationUtils() {
        throw new IllegalStateException("Utility class");
    }
}
