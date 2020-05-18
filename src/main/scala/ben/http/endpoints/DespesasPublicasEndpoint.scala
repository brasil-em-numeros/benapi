package ben.http.endpoints

import ben.domain.DespesaPublicaExecucao
import ben.environment.storage.despesaspublicas.{ DespesasPublicasExecucaoStorage => DespesasStorage }
import io.circe.generic.auto._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class DespesasPublicasEndpoint[R <: DespesasStorage] {
  type DespesasPublicasTask[A] = RIO[R, A]
  type DespesasPublicasStream = fs2.Stream[DespesasPublicasTask, DespesaPublicaExecucao]

  private val prefixPath = "/despesas-publicas"

  val dsl = Http4sDsl[DespesasPublicasTask]
  import dsl._

  private val httpRoutes = HttpRoutes.of[DespesasPublicasTask] {
    case GET -> Root =>
      val pipeline: DespesasPublicasTask[DespesasPublicasStream] = DespesasStorage.all
      pipeline
        .foldM(_ => NotFound(), Ok(_))

    case GET -> Root / IntVar(id) =>
      DespesasStorage.byId(id).foldM(_ => NotFound(), Ok(_))
  }

  val routes: HttpRoutes[DespesasPublicasTask] = Router(
    prefixPath -> httpRoutes
  )
}
