# 2-check-status.ps1
# Vérifie l'état de SonarQube

Write-Host "=== Vérification de l'état de SonarQube ===" -ForegroundColor Cyan

# Vérifier si le conteneur existe et son état
Write-Host "`nÉtat du conteneur SonarQube:" -ForegroundColor Yellow
docker ps -a --filter "name=sonarqube" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

# Vérifier les volumes
Write-Host "`nVolumes Docker:" -ForegroundColor Yellow
docker volume ls --filter "name=sonarqube"

# Afficher les derniers logs
Write-Host "`nDerniers logs (10 lignes):" -ForegroundColor Yellow
docker logs --tail 10 sonarqube

Write-Host "`n=== Fin de la vérification ===" -ForegroundColor Green
Write-Host "URL d'accès : http://localhost:9000" -ForegroundColor Cyan
