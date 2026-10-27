# Challenge Nuclear - DevOps Tools & Cloud Computing (Sprint 1)

## Integrantes
| Nome | RM | Turma |
|------|----|-------|
| Murillo Kenzo | PREENCHER | PREENCHER |
| PREENCHER | PREENCHER | PREENCHER |

## Descricao da solucao
API REST (Java Spring Boot) de apoio a uma solucao educativa sobre tecnologia nuclear. Cadastra aplicacoes da tecnologia nuclear (saude, agricultura, industria, energia, pesquisa). Roda em containers Docker numa VM do Azure, com banco Oracle.

## Arquitetura
VM Ubuntu no Azure (eastus) com dois containers na rede Docker `rede-app`: `api-nuclear` (Spring Boot, porta 8080) e `oracle-db` (Oracle Free). Os dados ficam no volume `oracle_data`.

Diagrama: `diagrama-arquitetura.png`

## Ferramentas e versoes
| Ferramenta | Versao |
|------------|--------|
| Ubuntu | 24.04 LTS |
| Docker | 29.1.3 |
| Docker Compose | 2.40.3 |
| Azure CLI | PREENCHER (az version) |
| Java / Spring Boot | 21 / 3.3.5 |
| Oracle Free (imagem) | gvenzl/oracle-free:23-slim |

## Como executar
1. No Azure Cloud Shell (Bash):
   ```bash
   export VM_PASS='SuaSenhaForte@123'
   ./criarvm.sh
   ```
2. Conectar: `ssh azureuser@IP_PUBLICO`
3. Instalar Docker: `sudo apt update && sudo apt install -y docker.io docker-compose-v2 && sudo usermod -aG docker $USER` (reconectar)
4. Clonar o repositorio e criar o `.env` a partir do `.env.example`
5. Subir: `docker compose up -d --build`
6. Aguardar ~2 min e testar: `curl http://localhost:8080/aplicacoes`

## Endpoints
| Metodo | Rota | Acao |
|--------|------|------|
| GET | /aplicacoes | Lista todos |
| GET | /aplicacoes/{id} | Busca um |
| POST | /aplicacoes | Cria |
| PUT | /aplicacoes/{id} | Atualiza |
| DELETE | /aplicacoes/{id} | Remove |

## Testes
CRUD completo pela API (minimo 5 registros, ver `init.sql`) e persistencia com `docker compose down` / `up -d`.

## Exclusao dos recursos
`az group delete --name rg-challenge-nuclear --yes --no-wait`
