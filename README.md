# benapi

Backend API do brasil-em-numeros

## Rodando a api

Para buildar a aplicação, execute `build/setup.sh`
- Só é necessário ser executado quando há mudanças

Para iniciar o servidor na porta 8080: `./run.sh`

## Endpoints

`http://localhost:8080/api/v1/despesas-publicas` => stream de todos os dados

`http://localhost:8080/api/v1/despesas-publicas/{id}` => busca por id