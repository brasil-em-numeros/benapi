package ben.http

import ben.environment.config.HttpServerConfig
import ben.environment.Environments.AppEnvironment
import ben.http.endpoints.{DespesasPublicasEndpoint, HealthEndpoint}
import cats.data.Kleisli
import cats.effect.ExitCode
import cats.implicits._
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.Router
import org.http4s.{Request, Response}
import zio.interop.catz._
import zio.{RIO, ZIO}

object Server {
  type ServerRIO[A] = RIO[AppEnvironment, A]
  type ServerRoutes = Kleisli[ServerRIO, Request[ServerRIO], Response[ServerRIO]]

  def runServer: ZIO[AppEnvironment, Nothing, Unit] =
    ZIO.runtime[AppEnvironment].flatMap { implicit rts =>
      val cfg = rts.environment.get[HttpServerConfig]

      BlazeServerBuilder[ServerRIO]
        .bindHttp(cfg.port, cfg.host)
        .withHttpApp(createRoutes(cfg.path))
        .serve
        .compile[ServerRIO, ServerRIO, ExitCode]
        .drain
    }
    .orDie

  def createRoutes(basePath: String): ServerRoutes = {
    val healthRoutes = new HealthEndpoint[AppEnvironment].routes
    val despesasPublicasRoutes = new DespesasPublicasEndpoint[AppEnvironment].routes

    val openRoutes = healthRoutes <+> despesasPublicasRoutes

    Router[ServerRIO](basePath -> openRoutes).orNotFound
  }
}
