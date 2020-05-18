package ben.environment.storage.despesaspublicas

import ben.domain.DespesaPublicaExecucao
import ben.environment.storage.despesaspublicas.DespesasPublicasExecucaoStorage.Service
import doobie.implicits._
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import io.getquill._
import zio.Task
import zio.interop.catz._

private[despesaspublicas] final case class Doobie(xa: Transactor[Task]) extends Service {
  val ctx = new DoobieContext.H2(Literal)
  import ctx._

  implicit val schema = schemaMeta[DespesaPublicaExecucao]("ExecucaoDespesasPublicas")

  def all(): fs2.Stream[Task, DespesaPublicaExecucao] =
    ctx
      .stream(query[DespesaPublicaExecucao])
      .transact(xa)

  def byId(id: Long): Task[Option[DespesaPublicaExecucao]] =
    ctx
      .run(by(id))
      .transact(xa)
      .map(_.headOption)

  private def by(id: Long) = quote {
    query[DespesaPublicaExecucao].filter(_.id == lift(id))
  }
}