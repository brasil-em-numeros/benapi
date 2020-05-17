package ben.environment.repository.despesaspublicas

import ben.domain.DespesaPublicaExecucao
import ben.environment.repository.despesaspublicas.DespesasPublicasExecucaoStorage.Service
import doobie.implicits._
import doobie.util.query.Query0
import doobie.util.transactor.Transactor
import zio.interop.catz._
import zio.{Task, UIO}

private[despesaspublicas] final case class Doobie(xa: Transactor[Task]) extends Service {
  def all: UIO[List[DespesaPublicaExecucao]] =
    Queries
      .all
      .to[List]
      .transact(xa)
      .orDie

  def byId(id: Long): Task[Option[DespesaPublicaExecucao]] =
    Queries
      .byId(id)
      .option
      .transact(xa)
}

private object Queries {
  val all: Query0[DespesaPublicaExecucao] = sql"""
      SELECT * FROM ExecucaoDespesasPublicas
      """.query[DespesaPublicaExecucao]

  def byId(id: Long): Query0[DespesaPublicaExecucao] = sql"""
      SELECT * FROM ExecucaoDespesasPublicas where id = $id
      """.query[DespesaPublicaExecucao]
}