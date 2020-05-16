package ben.environment

import pureconfig.ConfigSource
import zio._

package object config {

  type Configuration = Has[DbConfig] with Has[HttpServerConfig]
  type ServerConfiguration = Has[HttpServerConfig]

  final case class DbConfig(url: String, user: String, password: String)
  final case class HttpServerConfig(host: String, port: Int, path: String)

  final case class AppConfig(database: DbConfig, httpServer: HttpServerConfig)

  val dbConfig: RIO[Has[DbConfig], DbConfig] = RIO.access(_.get)
  val httpServerConfig: RIO[Has[HttpServerConfig], HttpServerConfig] = RIO.access(_.get)

  object Configuration {
    import pureconfig.generic.auto._

    val live: TaskLayer[Configuration] = ZLayer.fromEffectMany(
      Task
        .effect(ConfigSource.default.loadOrThrow[AppConfig])
        .map(c => Has(c.database) ++ Has(c.httpServer))
    )
  }
}
