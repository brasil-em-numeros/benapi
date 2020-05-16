package ben.environment

import ben.environment.config.Configuration
import zio.clock.Clock

object Environments {

  type AppEnvironment = Configuration with Clock
}
