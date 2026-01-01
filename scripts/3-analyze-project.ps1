# 3-analyze-project.ps1
# Lance l'analyse SonarQube du projet

param(
    [Parameter(Mandatory=$false)]
    [string]$Token = "",
    
    [Parameter(Mandatory=$false)]
    [string]$ProjectKey = "Student_class"
)

Write-Host "=== Analyse SonarQube du projet ===" -ForegroundColor Cyan

# Vérifier si le token est fourni
if ([string]::IsNullOrEmpty($Token)) {
    Write-Host "`nERREUR: Le token SonarQube est requis!" -ForegroundColor Red
    Write-Host "Usage: .\3-analyze-project.ps1 -Token 'VOTRE_TOKEN'" -ForegroundColor Yellow
    Write-Host "       .\3-analyze-project.ps1 -Token 'VOTRE_TOKEN' -ProjectKey 'NomProjet'" -ForegroundColor Yellow
    exit 1
}

# Se déplacer dans le dossier du projet
$projectPath = Join-Path $PSScriptRoot "..\student-project"
if (Test-Path $projectPath) {
    Set-Location $projectPath
    Write-Host "`nDossier du projet : $projectPath" -ForegroundColor Green
} else {
    Write-Host "`nERREUR: Le dossier du projet n'existe pas : $projectPath" -ForegroundColor Red
    exit 1
}

# Lancer l'analyse Maven
Write-Host "`nLancement de l'analyse Maven SonarQube..." -ForegroundColor Yellow
Write-Host "Projet : $ProjectKey" -ForegroundColor Cyan
Write-Host "Cette opération peut prendre quelques minutes...`n" -ForegroundColor Yellow

mvn clean verify sonar:sonar `
  -Dsonar.projectKey=$ProjectKey `
  -Dsonar.host.url=http://localhost:9000 `
  -Dsonar.token=$Token

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n=== Analyse terminée avec succès! ===" -ForegroundColor Green
    Write-Host "Consultez les résultats sur : http://localhost:9000/dashboard?id=$ProjectKey" -ForegroundColor Cyan
} else {
    Write-Host "`n=== Erreur lors de l'analyse ===" -ForegroundColor Red
    Write-Host "Vérifiez les logs ci-dessus pour plus de détails." -ForegroundColor Yellow
}
