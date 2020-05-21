package ben.http.endpoints

import ben.domain.despesaspublicas.Execucao
import ben.environment.storage.despesaspublicas.ExecucaoStorage
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.RIO

final class DespesasPublicasEndpoint[R <: ExecucaoStorage] {
  type DespesasPublicasTask[A] = RIO[R, A]
  type DespesasPublicasStream = fs2.Stream[DespesasPublicasTask, Execucao]

  private val prefixPath = "/despesas-publicas"

  val dsl = Http4sDsl[DespesasPublicasTask]
  import dsl._

  object COSQueryParameter extends QueryParamDecoderMatcher[Long]("codigoOrgaoSuperior")

  private val httpRoutes = HttpRoutes.of[DespesasPublicasTask] {
    case GET -> Root :? COSQueryParameter(codigo) ⇒
      val pipeline: DespesasPublicasTask[DespesasPublicasStream] = ExecucaoStorage.byCodigo(codigo)
      pipeline
        .foldM(_ ⇒ NotFound(), Ok(_))

    case GET -> Root / IntVar(id) ⇒
      ExecucaoStorage.byId(id).foldM(_ ⇒ NotFound(), Ok(_))
  }

  val routes: HttpRoutes[DespesasPublicasTask] = Router(
    prefixPath -> httpRoutes
  )
}
