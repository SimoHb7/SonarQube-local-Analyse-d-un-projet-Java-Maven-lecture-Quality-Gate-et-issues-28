# Scripts Docker pour SonarQube - TP28

## 1-setup-sonarqube.ps1
# Crée les volumes et démarre SonarQube

Write-Host "=== Configuration de SonarQube ===" -ForegroundColor Cyan

# Créer les volumes Docker
Write-Host "`nCréation des volumes Docker..." -ForegroundColor Yellow
docker volume create sonarqube_data
docker volume create sonarqube_logs
docker volume create sonarqube_extensions

Write-Host "`nVolumes créés avec succès!" -ForegroundColor Green

# Démarrer SonarQube
Write-Host "`nDémarrage de SonarQube..." -ForegroundColor Yellow
docker run -d --name sonarqube -p 9000:9000 `
  -v sonarqube_data:/opt/sonarqube/data `
  -v sonarqube_logs:/opt/sonarqube/logs `
  -v sonarqube_extensions:/opt/sonarqube/extensions `
  sonarqube:lts-community

Write-Host "`nSonarQube démarré avec succès!" -ForegroundColor Green
Write-Host "`nAttendez 1-2 minutes que SonarQube soit complètement démarré..." -ForegroundColor Yellow
Write-Host "Puis ouvrez : http://localhost:9000" -ForegroundColor Cyan
Write-Host "Identifiants par défaut : admin / admin" -ForegroundColor Cyan
