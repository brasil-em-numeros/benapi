package ben.domain

final case class DespesaPublicaExecucao(
  id: Long,
  lancamento: String,
  codigoOrgaoSuperior: Int,
  nomeOrgaoSuperior: String,
  codigoOrgaoSubordinado: Int,
  nomeOrgaoSubordinado: String
)

