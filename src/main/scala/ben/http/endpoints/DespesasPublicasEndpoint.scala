package ben.http.endpoints

import ben.domain.DespesaPublicaExecucao
import ben.environment.repository.despesaspublicas._
import io.circe.generic.auto._
import io.circe.Encoder
import org.http4s.circe._
import org.http4s.{EntityEncoder, HttpRoutes}
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router
import zio.interop.catz._
import zio.{RIO, Task}

final class DespesasPublicasEndpoint[R <: DespesasPublicasExecucaoStorage] {
  type DespesasPublicasRIO[A] = RIO[R, A]
  type DespesasPublicasStream = fs2.Stream[DespesasPublicasRIO, DespesaPublicaExecucao]

  private val prefixPath = "/despesas-publicas"

  implicit def circeJsonEncoder[A](implicit encoder: Encoder[A]): EntityEncoder[DespesasPublicasRIO, A] =
    jsonEncoderOf[DespesasPublicasRIO, A]

  implicit def streamEncoder[A](implicit encoder: EntityEncoder[Task, A]): EntityEncoder[Task, fs2.Stream[Task, A]] =
    EntityEncoder.streamEncoder[Task, A]

  val dsl = Http4sDsl[DespesasPublicasRIO]
  import dsl._

  private val httpRoutes = HttpRoutes.of[DespesasPublicasRIO] {
    case GET -> Root =>
      val pipeline: DespesasPublicasRIO[DespesasPublicasStream] = DespesasPublicasExecucaoStorage.all
      pipeline
        .foldM(_ => NotFound(), Ok(_))

    case GET -> Root / IntVar(id) =>
      DespesasPublicasExecucaoStorage.byId(id).foldM(_ => NotFound(), Ok(_))
  }

  val routes: HttpRoutes[DespesasPublicasRIO] = Router(
    prefixPath -> httpRoutes
  )
}
