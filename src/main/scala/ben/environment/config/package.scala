package ben.environment

import pureconfig.ConfigSource
import zio._

package object config {

  type Configuration = Has[DbConfig] with Has[HttpServerConfig]
  type ServerConfiguration = Has[HttpServerConfig]
  type DbConfiguration = Has[DbConfig]

  final case class DbConfig(driver: String, url: String, user: String, password: String)
  final case class HttpServerConfig(host: String, port: Int, path: String)

  final case class AppConfig(database: DbConfig, httpServer: HttpServerConfig)

  val dbConfig: RIO[Has[DbConfig], DbConfig] = RIO.access(_.get)
  val httpServerConfig: RIO[Has[HttpServerConfig], HttpServerConfig] = RIO.access(_.get)

  object Configuration {
    import pureconfig.generic.auto._

    val live: ULayer[Configuration] = ZLayer.fromEffectMany(
      ZIO
        .effect(ConfigSource.default.loadOrThrow[AppConfig])
        .map(c ⇒ Has(c.database) ++ Has(c.httpServer))
        .orDie
    )
  }
}
