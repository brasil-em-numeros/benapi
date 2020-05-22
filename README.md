# benapi

Backend API do brasil-em-numeros

## Rodando o servidor

Para iniciar o servidor na porta 8080: `./run.sh`

## Endpoints

`http://localhost:8080/api/v1/despesas-publicas/{id}` => busca por id (para facilitar testes)

`http://localhost:8080/api/v1/despesas-publicas?codigoOrgaoSuperior={codigo}` => stream de todos os dados
de um dado orgão superior. As possiblidades são

| Código Órgão Superior | Nome Órgão Superior |
|---|---|
|20000 | Presidência da República |
|22000 | Ministério da Agricultura, Pecuária e Abastec |
|24000 | Ministério da Ciência, Tecnologia, Inovações |
|25000 | Ministério da Economia |
|26000 | Ministério da Educação |
|30000 | Ministério da Justiça e Segurança Pública |
|32000 | Ministério de Minas e Energia |
|33000 | Ministério da Previdência Social |
|35000 | Ministério das Relações Exteriores |
|36000 | Ministério da Saúde |
|37000 | Controladoria-Geral da União |
|39000 | Ministério da Infraestrutura |
|44000 | Ministério do Meio Ambiente |
|52000 | Ministério da Defesa |
|53000 | Ministério do Desenvolvimento Regional |
|54000 | Ministério do Turismo |
|55000 | Ministério da Cidadania |
|63000 | Advocacia-Geral da União |
|81000 | Ministério da Mulher, Família e Direitos Huma |