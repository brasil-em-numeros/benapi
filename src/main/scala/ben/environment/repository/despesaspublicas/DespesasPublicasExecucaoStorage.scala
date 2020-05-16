package ben.environment.repository.despesaspublicas

import ben.domain.DespesaPublicaExecucao
import ben.environment.repository.DbTransactor
import zio._

object DespesasPublicasExecucaoStorage {
  type DespesasPublicasExecucaoStorage = Has[Service]

  trait Service {
    def all: UIO[List[DespesaPublicaExecucao]]
  }

  val doobie: URLayer[Has[DbTransactor], DespesasPublicasExecucaoStorage] =
    ZLayer.fromService[DbTransactor, Service] { dbTransactor =>
      Doobie(dbTransactor.get.xa)
    }
}
