CREATE TABLE IF NOT EXISTS DespesasPublicasExecucao(
    id bigint auto_increment,
    lancamento varchar,
    codigoOrgaoSuperior int,
    nomeOrgaoSuperior varchar,
    codigoOrgaoSubordinado int,
    nomeOrgaoSubordinado varchar,
    codigoUnidadeGestora int,
    nomeUnidadeGestora varchar,
    codigoGestao int,
    nomeGestao varchar,
    codigoUnidadeOrcamentaria int,
    nomeUnidadeOrcamentaria varchar,
    codigoFuncao smallint,
    nomeFuncao varchar,
    codigoSubfuncao smallint ,
    nomeSubfuncao varchar,
    codigoProgramaOrcamentario smallint,
    nomeProgramaOrcamentario varchar,
    codigoAcao varchar,
    nomeAcao varchar,
    codigoPlanoOrcamentario varchar,
    planoOrcamentario varchar,
    codigoProgramaGoverno varchar,
    nomeProgramaGoverno varchar,
    codigoGrupoDespesa tinyint,
    nomeGrupoDespesa varchar,
    codigoElementoDespesa smallint,
    nomeElementoDespesa varchar,
    codigoModalidadeDespesa tinyint,
    modalidadeDespesa varchar,
    valorEmpenhado decimal,
    valorLiquidado decimal,
    valorPago decimal,
    valorRestosAPagarInscritos decimal,
    valorRestosAPagarCancelado decimal,
    valorRestosAPagarPagos decimal
)

/* INSERT INTO DespesasPublicasExecucao
  SELECT null, * FROM CSVREAD('/tmp/pdt/202005.csv',null,'fieldSeparator=;')

SHUTDOWN COMPACT
  */