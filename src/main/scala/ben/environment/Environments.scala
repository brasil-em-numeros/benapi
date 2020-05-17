package ben.environment

import ben.environment.config.Configuration
import ben.environment.repository.DbTransactor
import ben.environment.repository.despesaspublicas.DespesasPublicasExecucaoStorage
import zio.ULayer
import zio.clock.Clock

object Environments {

  type HttpServerEnvironment = Configuration with Clock

  type DespesasPublicasEnvironment = HttpServerEnvironment with DespesasPublicasExecucaoStorage

  type AppEnvironment =
    Clock
      with Configuration
      with DespesasPublicasExecucaoStorage

  val httpServerEnvironment: ULayer[HttpServerEnvironment] =
    Configuration.live ++ Clock.live

  val dbTransactor: ULayer[DbTransactor] = Configuration.live >>> DbTransactor.live

  val despesasPublicasExecucaoStorage: ULayer[DespesasPublicasExecucaoStorage] =
    dbTransactor >>> DespesasPublicasExecucaoStorage.doobie

  val appEnvironment: ULayer[AppEnvironment] =
    httpServerEnvironment ++ despesasPublicasExecucaoStorage
}
