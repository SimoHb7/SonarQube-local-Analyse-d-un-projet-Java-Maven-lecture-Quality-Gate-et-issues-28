# TP 28 : SonarQube (local) — Analyse d'un projet Java Maven

**Cours** : Architecture Microservices : Conception, Déploiement et Orchestration

## Objectif de l'activité

Mettre en place SonarQube en local (Docker), créer un projet, générer un token, lancer l'analyse d'un projet Java Maven, puis interpréter les résultats (Quality Gate, bugs, odeurs de code, vulnérabilités, couverture…).

---

## Prérequis

- ✅ Docker Desktop (ou Docker Engine)
- ✅ Navigateur Web
- ✅ JDK installé (Java 11 ou supérieur)
- ✅ Maven (ou Maven Wrapper `mvnw`)
- ✅ Un projet Java Maven (présence de `pom.xml`)

---

## Étape 1 — Démarrer SonarQube en local (Docker)

### 1.1 Créer les volumes Docker (persistance)

Ces volumes gardent les données SonarQube entre les redémarrages (plugins, logs, index…).

**Commande** :

```powershell
docker volume create sonarqube_data
docker volume create sonarqube_logs
docker volume create sonarqube_extensions
```

> **💡 Remarque (débutant)**  
> Sans volumes, tout est perdu quand le conteneur est supprimé (projets, règles, historiques…).

---

### 1.2 Lancer SonarQube

**Exemple recommandé (édition Community LTS)** :

**Windows PowerShell** :
```powershell
docker run -d --name sonarqube -p 9000:9000 `
  -v sonarqube_data:/opt/sonarqube/data `
  -v sonarqube_logs:/opt/sonarqube/logs `
  -v sonarqube_extensions:/opt/sonarqube/extensions `
  sonarqube:lts-community
```

**Linux/Mac** :
```bash
docker run -d --name sonarqube -p 9000:9000 \
  -v sonarqube_data:/opt/sonarqube/data \
  -v sonarqube_logs:/opt/sonarqube/logs \
  -v sonarqube_extensions:/opt/sonarqube/extensions \
  sonarqube:lts-community
```

> **⚠️ Remarque**  
> - Si le port 9000 est occupé : utiliser `-p 9001:9000`, puis ouvrir `http://localhost:9001`
> - Sur Linux, SonarQube peut exiger un paramètre système pour Elasticsearch (`vm.max_map_count`)

---

### 1.3 Vérifier l'accès web

1. Ouvrir : **http://localhost:9000**
2. **Identifiants par défaut** : `admin` / `admin`
3. SonarQube demande ensuite de **changer le mot de passe**

> **⏱️ Temps de démarrage** : Attendre 1-2 minutes que SonarQube soit complètement démarré

---

## Étape 2 — Comprendre l'écran "Overview" et le Quality Gate

Après connexion, SonarQube affiche un tableau de bord où le **Quality Gate** indique si le projet respecte les critères minimaux (bugs, vulnérabilités, coverage, duplications… selon la configuration).

### 2.1 Exemple "Quality Gate Failed"

Un Quality Gate peut échouer si, par exemple, il y a trop de bugs ou une couverture trop faible.

### 2.2 Exemple "Quality Gate Passed"

Quand toutes les conditions sont satisfaites, le projet est "Passed".

> **💡 Remarque (débutant)**  
> Le but n'est pas "zéro problème" immédiatement, mais de :
> 1. Corriger les **Bugs** et **Vulnérabilités** en priorité
> 2. Réduire progressivement les **Code Smells** (maintenabilité)
> 3. Améliorer la **couverture tests**

---

## Étape 3 — Créer un projet SonarQube (mode manuel / local)

### 3.1 Ouvrir "Projects"

Aller dans la barre du haut → **Projects**

### 3.2 Cliquer sur "Create Project"

En haut à droite, généralement un bouton **Create Project**

### 3.3 Menu "Create Project" (choix Manually)

Dans certains écrans, "Create Project" ouvre un menu déroulant (Manually / More)

### 3.4 Choisir "Manually" (projet local)

Si le code est sur la machine (pas sur GitHub/GitLab intégré à SonarQube), choisir **Manually**

### 3.5 Écran alternatif : connexion DevOps (GitHub/GitLab…)

SonarQube propose aussi une création via plateformes DevOps (utile en entreprise/CI)

### 3.6 Renseigner "Project display name" et "Project key"

- **Project display name** : nom lisible (ex : `Student_class`)
- **Project key** : identifiant unique (souvent identique au nom)

> **💡 Remarque (débutant)**  
> - La `project key` est utilisée par la commande Maven (`-Dsonar.projectKey=...`)
> - Éviter espaces; utiliser `_` ou `-`

---

## Étape 4 — Choisir "Analyser localement"

Après création, SonarQube demande comment analyser le dépôt :

- **CI** (Jenkins/GitHub Actions/GitLab…)
- ou **Localement** (TP)

➡️ Choisir **Locally**

---

## Étape 5 — Générer un token (obligatoire)

Le token sert d'authentification pour autoriser l'analyse.

### 5.1 Générer un "project token"

- **Token name** (ex : `Analyze "Student_class"`)
- **Expiration** (ex : `30 days`)
- Cliquer **Generate**

### 5.2 Récupérer le token généré

Copier et garder le token en lieu sûr.

> **🔒 Remarque importante (sécurité)**  
> - Le token est un **secret** : ne pas le publier, ne pas le commiter
> - Si un token apparaît dans des captures partagées, il faut le **révoquer** et en régénérer un

---

## Étape 6 — Choisir le scanner Maven et exécuter l'analyse

### 6.1 SonarQube propose la commande selon le build (Maven/Gradle/…)

Choisir **Maven** (si le projet est Maven)

### 6.2 Copier la commande Maven SonarScanner

SonarQube affiche une commande similaire à :

**Windows PowerShell** :
```powershell
mvn clean verify sonar:sonar `
  -Dsonar.projectKey=Student_class `
  -Dsonar.host.url=http://localhost:9000 `
  -Dsonar.token=VOTRE_TOKEN
```

**Linux/Mac (bash/zsh)** :
```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=Student_class \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=VOTRE_TOKEN
```

> **💡 Remarque (débutant)**  
> - `clean verify` → compile + lance les tests (si présents)
> - `sonar:sonar` → envoie le rapport à SonarQube
> - Si SonarQube tourne sur un autre port (ex 9001), modifier `sonar.host.url`

---

### 6.3 Se placer dans le dossier du projet Maven

Le dossier doit contenir `pom.xml`

```powershell
cd student-project
```

---

### 6.4 Lancer la commande d'analyse

Ouvrir un terminal dans ce dossier puis exécuter la commande.

**Résultat attendu** :
- Maven télécharge des dépendances (si nécessaire)
- Lance les tests
- Envoie l'analyse vers SonarQube
- Message final proche de : **"ANALYSIS SUCCESSFUL"**

---

## Étape 7 — Consulter les résultats dans SonarQube

### 7.1 Ouvrir le projet

Dans SonarQube : **Projects** → sélectionner `Student_class`

### 7.2 Lire les sections principales

- **Overview** : résumé + Quality Gate
- **Issues** : liste détaillée (Bugs, Code Smells…)
- **Security Hotspots** : points à valider (revue sécurité)
- **Measures** : métriques (duplication, complexité…)
- **Code** : code annoté + explications règle par règle
- **Activity** : historique des analyses

> **💡 Remarque (débutant) : ordre conseillé pour corriger**  
> 1. **Bugs**
> 2. **Vulnerabilities** / **Hotspots**
> 3. **Code Smells** (petit à petit)
> 4. **Coverage** (ajout de tests)

---

## Dépannage (problèmes fréquents)

| Problème | Solution |
|----------|----------|
| **Erreur 401 / Unauthorized** | Token invalide → régénérer et relancer |
| **Connection refused** | SonarQube non démarré / mauvaise URL |
| **Projet introuvable** | `sonar.projectKey` différent de celui créé dans SonarQube |
| **Analyse très lente** | Machine chargée / SonarQube pas encore "Ready" |
| **Port 9000 occupé** | Utiliser `-p 9001:9000` et adapter l'URL |

---

## Mini-récap (à écrire dans le compte rendu)

✅ SonarQube tourne en local via Docker sur `localhost:9000`  
✅ Projet créé en mode **Manually**  
✅ Token généré et utilisé dans Maven  
✅ Analyse déclenchée via `mvn clean verify sonar:sonar`  
✅ Lecture du Quality Gate et des issues (priorité : bugs + sécurité)

---

## Commandes utiles Docker

### Vérifier l'état du conteneur
```powershell
docker ps -a
```

### Voir les logs SonarQube
```powershell
docker logs sonarqube
```

### Arrêter SonarQube
```powershell
docker stop sonarqube
```

### Redémarrer SonarQube
```powershell
docker start sonarqube
```

### Supprimer le conteneur (⚠️ les volumes restent)
```powershell
docker rm sonarqube
```

### Supprimer les volumes (⚠️ perte de données)
```powershell
docker volume rm sonarqube_data sonarqube_logs sonarqube_extensions
```

---

## Ressources

- [SonarQube Documentation](https://docs.sonarqube.org/)
- [SonarQube Docker Hub](https://hub.docker.com/_/sonarqube)
- [Maven SonarScanner Plugin](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-maven/)

---

**Auteur** : TP Architecture Microservices  
**Date** : Janvier 2026
