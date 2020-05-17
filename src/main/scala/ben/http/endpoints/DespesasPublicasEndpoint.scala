package ben.http.endpoints

import ben.environment.repository.despesaspublicas._
import io.circe.generic.auto._
import io.circe.Encoder
import org.http4s.circe._
import org.http4s.{EntityEncoder, HttpRoutes}
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class DespesasPublicasEndpoint[R <: DespesasPublicasExecucaoStorage] {
  type DespesasPublicasTask[A] = RIO[R, A]

  private val prefixPath = "/despesas-publicas"

  implicit def circeJsonEncoder[A](implicit decoder: Encoder[A]): EntityEncoder[DespesasPublicasTask, A] =
    jsonEncoderOf[DespesasPublicasTask, A]

  val dsl = Http4sDsl[DespesasPublicasTask]
  import dsl._

  private val httpRoutes = HttpRoutes.of[DespesasPublicasTask] {
    case GET -> Root =>
      DespesasPublicasExecucaoStorage.all.foldM(_ => NotFound(), Ok(_))
  }

  val routes: HttpRoutes[DespesasPublicasTask] = Router(
    prefixPath -> httpRoutes
  )
}
