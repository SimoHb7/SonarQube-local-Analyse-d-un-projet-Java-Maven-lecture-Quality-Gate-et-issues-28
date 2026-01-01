# 5-cleanup.ps1
# Nettoie complètement SonarQube (conteneur + volumes)

Write-Host "=== Nettoyage de SonarQube ===" -ForegroundColor Cyan
Write-Host "ATTENTION: Cette opération supprimera TOUTES les données SonarQube!" -ForegroundColor Red

$confirmation = Read-Host "`nÊtes-vous sûr de vouloir continuer? (oui/non)"

if ($confirmation -eq "oui") {
    # Arrêter le conteneur
    Write-Host "`nArrêt du conteneur..." -ForegroundColor Yellow
    docker stop sonarqube 2>$null
    
    # Supprimer le conteneur
    Write-Host "Suppression du conteneur..." -ForegroundColor Yellow
    docker rm sonarqube 2>$null
    
    # Supprimer les volumes
    Write-Host "Suppression des volumes..." -ForegroundColor Yellow
    docker volume rm sonarqube_data sonarqube_logs sonarqube_extensions 2>$null
    
    Write-Host "`n=== Nettoyage terminé ===" -ForegroundColor Green
    Write-Host "Pour réinstaller SonarQube, exécutez : .\1-setup-sonarqube.ps1" -ForegroundColor Cyan
} else {
    Write-Host "`nOpération annulée." -ForegroundColor Yellow
}
