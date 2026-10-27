#!/bin/bash
# Uso: export VM_PASS='SuaSenhaForte@123' && ./criarvm.sh

RG="rg-challenge-nuclear"
LOCATION="eastus"
VM_NAME="vm-challenge"
ADMIN_USER="azureuser"

if [ -z "$VM_PASS" ]; then
  echo "Defina a senha antes: export VM_PASS='SuaSenhaForte@123'"
  exit 1
fi

# 1. Grupo de recursos
az group create --name $RG --location $LOCATION

# 2. VM Ubuntu 24.04
az vm create \
  --resource-group $RG \
  --name $VM_NAME \
  --image Canonical:ubuntu-24_04-lts:server:latest \
  --size Standard_D2s_v4 \
  --admin-username $ADMIN_USER \
  --admin-password "$VM_PASS" \
  --authentication-type password \
  --public-ip-sku Standard

# 3. Libera a porta da API (a 22 do SSH o Azure ja abre)
az vm open-port --resource-group $RG --name $VM_NAME --port 8080 --priority 1010
