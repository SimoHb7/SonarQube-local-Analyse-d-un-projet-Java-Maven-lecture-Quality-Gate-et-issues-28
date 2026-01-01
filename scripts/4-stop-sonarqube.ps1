# 4-stop-sonarqube.ps1
# Arrête SonarQube

Write-Host "=== Arrêt de SonarQube ===" -ForegroundColor Cyan

Write-Host "`nArrêt du conteneur SonarQube..." -ForegroundColor Yellow
docker stop sonarqube

if ($LASTEXITCODE -eq 0) {
    Write-Host "`nSonarQube arrêté avec succès!" -ForegroundColor Green
    Write-Host "Pour le redémarrer, utilisez : docker start sonarqube" -ForegroundColor Cyan
} else {
    Write-Host "`nErreur lors de l'arrêt de SonarQube" -ForegroundColor Red
}
