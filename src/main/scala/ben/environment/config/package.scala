package ben.environment

import ben.environment.config.Configuration._
import zio.Has

package object config {

  type Configuration = Has[DbConfig] with Has[HttpServerConfig]
  type ServerConfiguration = Has[HttpServerConfig]
  type DbConfiguration = Has[DbConfig]
}
