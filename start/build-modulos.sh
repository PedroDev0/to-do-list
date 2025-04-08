#!/bin/bash
set -e

# Diretório onde o script está localizado
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Diretório pai do diretório do script
BASEDIR="$(dirname "$SCRIPT_DIR")"

# Navega até o diretório pai e executa o build
cd "$BASEDIR"
./gradlew build

# Caminhos dos arquivos JAR
TAREFA_SERVICE_ORIGEM="$BASEDIR/modules/tarefa/tarefa-service/build/libs/com.pedro.dev.tarefa.service-1.0.0.jar"
TAREFA_API_ORIGEM="$BASEDIR/modules/tarefa/tarefa-api/build/libs/com.pedro.dev.tarefa.api-1.0.0.jar"
TO_DO_LIST="$BASEDIR/modules/to-do-list-widget/build/libs/com.pedro.dev.todolistwidget-1.0.0.jar"

# Diretório de destino
DESTINO="$SCRIPT_DIR/deploy"
if [ ! -d "$DESTINO" ]; then
  mkdir -p "$DESTINO"
fi

# Move os arquivos JAR para o diretório de destino
mv "$TAREFA_SERVICE_ORIGEM" "$DESTINO"
mv "$TAREFA_API_ORIGEM" "$DESTINO"
mv "$TO_DO_LIST" "$DESTINO"
