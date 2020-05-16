package ben.environment

import ben.environment.config.Configuration
import ben.environment.repository.despesaspublicas.DespesasPublicasExecucaoStorage.DespesasPublicasExecucaoStorage
import zio.clock.Clock

object Environments {

  type HttpServerEnvironment = Configuration with Clock

  type DespesasPublicasEnvironment = HttpServerEnvironment with DespesasPublicasExecucaoStorage

  type AppEnvironment =
    Clock
      with Configuration
      with DespesasPublicasExecucaoStorage
}
