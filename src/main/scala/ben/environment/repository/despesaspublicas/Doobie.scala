package ben.environment.repository.despesaspublicas

import ben.domain.DespesaPublicaExecucao
import ben.environment.repository.despesaspublicas.DespesasPublicasExecucaoStorage.Service
import doobie.implicits._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import zio.interop.catz._
import zio.{Task, UIO}

private[despesaspublicas] final case class Doobie(xa: Transactor[Task]) extends Service {
  val ctx = new DoobieContext.H2(Literal)
  import ctx._

  implicit val schema = schemaMeta[DespesaPublicaExecucao]("ExecucaoDespesasPublicas")

  def all: UIO[List[DespesaPublicaExecucao]] =
    ctx
      .run(query[DespesaPublicaExecucao])
      .transact(xa)
      .orDie

  def byId(id: Long): Task[Option[DespesaPublicaExecucao]] =
    ctx
      .run(by(id))
      .transact(xa)
      .map(_.headOption)

  private def by(id: Long) = quote {
    query[DespesaPublicaExecucao].filter(_.id == lift(id))
  }
}