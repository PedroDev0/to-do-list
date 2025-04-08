@echo off
setlocal

:: Caminho absoluto do diretório onde o script está
set "SCRIPT_DIR=%~dp0"

:: Sobe um nível: diretório pai
for %%i in ("%SCRIPT_DIR%\..") do set "BASEDIR=%%~fi"

:: Vai até o diretório pai e roda o build
cd /d "%BASEDIR%"
call gradlew.bat build

:: Caminhos dos arquivos JAR no diretório pai
set "TAREFA_SERVICE_ORIGEM=%BASEDIR%\modules\tarefa\tarefa-service\build\libs\com.pedro.dev.tarefa.service-1.0.0.jar"
set "TAREFA_API_ORIGEM=%BASEDIR%\modules\tarefa\tarefa-api\build\libs\com.pedro.dev.tarefa.api-1.0.0.jar"
set "TO_DO_LIST=%BASEDIR%\modules\to-do-list-widget\build\libs\com.pedro.dev.todolistwidget-1.0.0.jar"


set "DESTINO=%SCRIPT_DIR%deploy"
:: Cria o diretório de destino se ele não existir
if not exist "%DESTINO%" mkdir "%DESTINO%"

move "%TAREFA_SERVICE_ORIGEM%" "%DESTINO%"
move "%TAREFA_API_ORIGEM%" "%DESTINO%"
move "%TO_DO_LIST%" "%DESTINO%"

