package ben.domain

package object despesaspublicas {

  final case class Execucao(
    id: Long,
    lancamento: String,
    codigoOrgaoSuperior: Int,
    nomeOrgaoSuperior: String,
    codigoOrgaoSubordinado: Int,
    nomeOrgaoSubordinado: String,
    codigoUnidadeGestora: Int,
    nomeUnidadeGestora: String,
    codigoGestao: Int,
    nomeGestao: String,
    codigoUnidadeOrcamentaria: Int,
    nomeUnidadeOrcamentaria: String,
    codigoFuncao: Short,
    nomeFuncao: String,
    codigoSubfuncao: Short,
    nomeSubfuncao: String,
    codigoProgramaOrcamentario: Short,
    nomeProgramaOrcamentario: String,
    codigoAcao: String,
    nomeAcao: String,
    codigoPlanoOrcamentario: String,
    planoOrcamentario: String,
    codigoProgramaGoverno: String,
    nomeProgramaGoverno: String,
    codigoGrupoDespesa: Byte,
    nomeGrupoDespesa: String,
    codigoElementoDespesa: Short,
    nomeElementoDespesa: String,
    codigoModalidadeDespesa: Byte,
    modalidadeDespesa: String,
    valorEmpenhado: BigDecimal,
    valorLiquidado: BigDecimal,
    valorPago: BigDecimal,
    valorRestosAPagarInscritos: BigDecimal,
    valorRestosAPagarCancelado: BigDecimal,
    valorRestosAPagarPagos: BigDecimal
  )
}
