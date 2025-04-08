#!/bin/bash

# Diretório onde o script está localizado
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

# Executa o script de build dos módulos
"$SCRIPT_DIR/build-modulos.sh"

# Sobe os containers do Docker Compose com o nome do projeto 'to-do-list'
docker compose --project-name to-do-list up
