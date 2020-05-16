package ben.http.endpoints

import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class DespesasPublicasEndpoint[R] {
  type DespesasPublicasTask[A] = RIO[R, A]

  private val prefixPath = "/despesas-publicas"

  val dsl = Http4sDsl[DespesasPublicasTask]
  import dsl._

  private val httpRoutes = HttpRoutes.of[DespesasPublicasTask] {
    case GET -> Root => Ok("Despesas")
  }

  val routes: HttpRoutes[DespesasPublicasTask] = Router(
    prefixPath -> httpRoutes
  )
}
