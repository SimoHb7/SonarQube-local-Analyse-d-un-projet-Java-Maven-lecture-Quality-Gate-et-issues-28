# Scripts PowerShell pour TP28 - SonarQube

Ce dossier contient des scripts PowerShell pour faciliter la gestion de SonarQube.

## Scripts disponibles

### 1. `1-setup-sonarqube.ps1`
**Objectif** : Installation et démarrage initial de SonarQube

**Utilisation** :
```powershell
.\1-setup-sonarqube.ps1
```

**Actions** :
- Crée les volumes Docker nécessaires
- Démarre le conteneur SonarQube
- Affiche les instructions de connexion

---

### 2. `2-check-status.ps1`
**Objectif** : Vérifier l'état de SonarQube

**Utilisation** :
```powershell
.\2-check-status.ps1
```

**Actions** :
- Affiche l'état du conteneur
- Liste les volumes Docker
- Affiche les derniers logs

---

### 3. `3-analyze-project.ps1`
**Objectif** : Lancer l'analyse SonarQube du projet

**Utilisation** :
```powershell
# Avec le projet par défaut (Student_class)
.\3-analyze-project.ps1 -Token "VOTRE_TOKEN"

# Avec un autre projet
.\3-analyze-project.ps1 -Token "VOTRE_TOKEN" -ProjectKey "NomDuProjet"
```

**Paramètres** :
- `-Token` (obligatoire) : Token SonarQube généré depuis l'interface web
- `-ProjectKey` (optionnel) : Clé du projet (défaut: Student_class)

**Actions** :
- Se déplace dans le dossier du projet
- Exécute `mvn clean verify sonar:sonar`
- Envoie les résultats à SonarQube

---

### 4. `4-stop-sonarqube.ps1`
**Objectif** : Arrêter SonarQube

**Utilisation** :
```powershell
.\4-stop-sonarqube.ps1
```

**Actions** :
- Arrête le conteneur SonarQube
- Les données sont conservées dans les volumes

---

### 5. `5-cleanup.ps1`
**Objectif** : Supprimer complètement SonarQube

**Utilisation** :
```powershell
.\5-cleanup.ps1
```

**Actions** :
- Demande confirmation
- Arrête et supprime le conteneur
- Supprime tous les volumes (⚠️ perte de données)

---

## Ordre d'utilisation recommandé

1. **Installation** : `.\1-setup-sonarqube.ps1`
2. **Vérification** : `.\2-check-status.ps1`
3. Créer le projet et générer le token dans l'interface web
4. **Analyse** : `.\3-analyze-project.ps1 -Token "VOTRE_TOKEN"`
5. Consulter les résultats sur http://localhost:9000

---

## Commandes Docker utiles

```powershell
# Redémarrer SonarQube (après arrêt)
docker start sonarqube

# Voir tous les logs
docker logs sonarqube

# Voir les logs en temps réel
docker logs -f sonarqube

# Vérifier l'état
docker ps -a | Select-String "sonarqube"
```

---

## Dépannage

### Le port 9000 est déjà utilisé
Modifier le script `1-setup-sonarqube.ps1` :
```powershell
-p 9001:9000  # au lieu de -p 9000:9000
```
Puis accéder à http://localhost:9001

### SonarQube ne démarre pas
```powershell
# Vérifier les logs
docker logs sonarqube

# Redémarrer
docker restart sonarqube
```

### Erreur d'exécution de script PowerShell
```powershell
# Autoriser l'exécution des scripts (à faire une seule fois)
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```
