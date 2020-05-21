package ben.domain

import io.circe.Encoder
import io.circe.generic.semiauto._

package object despesaspublicas {

  object Execucao {
    implicit val execucaoEncoder: Encoder[Execucao] = deriveEncoder[Execucao]
  }

  final case class Execucao(
    id: Long,
    lancamento: Option[String],
    codigoOrgaoSuperior: Option[Int],
    nomeOrgaoSuperior: Option[String],
    codigoOrgaoSubordinado: Option[Int],
    nomeOrgaoSubordinado: Option[String],
    codigoUnidadeGestora: Option[Int],
    nomeUnidadeGestora: Option[String],
    codigoGestao: Option[Int],
    nomeGestao: Option[String],
    codigoUnidadeOrcamentaria: Option[Int],
    nomeUnidadeOrcamentaria: Option[String],
    codigoFuncao: Option[Short],
    nomeFuncao: Option[String],
    codigoSubfuncao: Option[Short],
    nomeSubfuncao: Option[String],
    codigoProgramaOrcamentario: Option[Short],
    nomeProgramaOrcamentario: Option[String],
    codigoAcao: Option[String],
    nomeAcao: Option[String],
    codigoPlanoOrcamentario: Option[String],
    planoOrcamentario: Option[String],
    codigoProgramaGoverno: Option[String],
    nomeProgramaGoverno: Option[String],
    codigoGrupoDespesa: Option[Byte],
    nomeGrupoDespesa: Option[String],
    codigoElementoDespesa: Option[Short],
    nomeElementoDespesa: Option[String],
    codigoModalidadeDespesa: Option[Byte],
    modalidadeDespesa: Option[String],
    valorEmpenhado: Option[BigDecimal],
    valorLiquidado: Option[BigDecimal],
    valorPago: Option[BigDecimal],
    valorRestosAPagarInscritos: Option[BigDecimal],
    valorRestosAPagarCancelado: Option[BigDecimal],
    valorRestosAPagarPagos: Option[BigDecimal]
  )
}
