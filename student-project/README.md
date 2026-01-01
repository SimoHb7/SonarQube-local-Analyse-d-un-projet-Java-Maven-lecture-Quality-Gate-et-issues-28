# Projet Student - Example pour SonarQube

Ce projet Java Maven contient **intentionnellement** des bugs et code smells pour démontrer les capacités de SonarQube.

## Structure du projet

```
student-project/
├── pom.xml                                    # Configuration Maven + SonarQube
└── src/
    ├── main/java/com/example/
    │   ├── model/
    │   │   └── Student.java                   # Classe modèle avec bugs
    │   ├── service/
    │   │   └── StudentService.java            # Service avec code smells
    │   └── util/
    │       └── ValidationUtils.java           # Utilitaires de validation
    └── test/java/com/example/
        ├── model/
        │   └── StudentTest.java               # Tests unitaires (couverture partielle)
        ├── service/
        │   └── StudentServiceTest.java
        └── util/
            └── ValidationUtilsTest.java
```

## Problèmes intentionnels inclus

### 🐛 Bugs

1. **Student.java** :
   - `equals()` redéfini sans `hashCode()`
   - Pas de validation des paramètres (âge, email, GPA)
   - Retourne des références mutables (`Date`)
   - Potentiel `NullPointerException` dans `equals()`

2. **StudentService.java** :
   - Division par zéro possible dans `calculateAverageGpa()`
   - `ConcurrentModificationException` dans `removeStudentByEmail()`
   - Expose la collection interne dans `getAllStudents()`
   - Blocs catch vides

### 🧩 Code Smells

1. **Variables non utilisées** :
   - `unusedField`, `anotherUnusedField` dans Student

2. **Code dupliqué** :
   - `getFullName()` et `getDisplayName()`
   - `countExcellentStudents()` et `countGoodStudents()`

3. **Complexité cyclomatique élevée** :
   - `getStudentStatus()` avec trop de conditions imbriquées

4. **Magic numbers** :
   - Constantes hardcodées (3.5, 25, 4.0, etc.)

5. **Utilisation de System.out.println** :
   - Au lieu d'un logger approprié

6. **Méthode toString() inefficace** :
   - Concaténation de String au lieu de StringBuilder

7. **Pattern recompilé** :
   - Dans `ValidationUtils.isValidEmail()`

8. **Méthodes mortes** :
   - `unusedMethod()` jamais appelée

9. **Trop de paramètres** :
   - `createStudent()` avec 9 paramètres

### 🔒 Vulnérabilités potentielles

1. **Injection SQL** :
   - `buildQuery()` utilise la concaténation de strings

2. **Références mutables exposées** :
   - `getEnrollmentDate()` retourne Date directement

### 📊 Couverture de tests

- Couverture intentionnellement **partielle** (~60-70%)
- Certaines méthodes ne sont pas testées
- Certains cas limites non couverts

## Lancer les tests

```powershell
cd student-project
mvn test
```

## Analyser avec SonarQube

```powershell
mvn clean verify sonar:sonar `
  -Dsonar.projectKey=Student_class `
  -Dsonar.host.url=http://localhost:9000 `
  -Dsonar.token=VOTRE_TOKEN
```

## Résultats attendus

Après l'analyse, vous devriez voir dans SonarQube :

- **Quality Gate** : Probablement **Failed** (en fonction de la configuration par défaut)
- **Bugs** : 5-10 bugs identifiés
- **Code Smells** : 20-30 code smells
- **Vulnerabilities** : 1-2 vulnérabilités
- **Security Hotspots** : À examiner
- **Coverage** : ~60-70%
- **Duplications** : ~5-10%

## Exercice de correction

Essayez de corriger progressivement :

1. **D'abord les bugs** (priorité maximale)
2. **Ensuite les vulnérabilités**
3. **Puis les code smells majeurs**
4. **Enfin améliorer la couverture**

Relancez l'analyse après chaque correction pour voir l'amélioration du Quality Gate !

---

**Note** : Ce projet est conçu uniquement à des fins pédagogiques pour démontrer SonarQube.
