@echo off
setlocal

:: Compila os módulos do projeto
call build-modulos.bat

:: Sobe os containers do Docker
docker compose --project-name to-do-list up
