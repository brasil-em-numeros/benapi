package ben.environment.storage.despesaspublicas

import ben.domain.despesaspublicas.Execucao
import ben.environment.storage.despesaspublicas.ExecucaoStorage.Service
import doobie.implicits._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import zio.Task
import zio.interop.catz._

private[despesaspublicas] final case class Doobie(xa: Transactor[Task]) extends Service {
  import Queries.ctx._

  def byId(id: Long): Task[Option[Execucao]] =
    run(Queries.by(id))
      .transact(xa)
      .map(_.headOption)

  def byCodigo(codigo: Long): fs2.Stream[Task, Execucao] =
    stream(Queries.byCod(codigo))
      .transact(xa)
}

private object Queries {
  val ctx = new DoobieContext.H2(Literal)
  import ctx._
  implicit val schema = schemaMeta[Execucao]("DespesasPublicasExecucao")

  def by(id: Long) = quote {
    query[Execucao].filter(_.id == lift(id))
  }

  def byCod(codigo: Long) = quote {
    query[Execucao].filter(_.codigoOrgaoSuperior.exists(_ == lift(codigo)))
  }
}