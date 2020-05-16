package ben.environment.repository

import ben.domain.DespesaPublicaExecucao
import zio._

package object despesaspublicas {

  type DespesasPublicasExecucaoStorage = Has[DespesasPublicasExecucaoStorage.Service]

  object DespesasPublicasExecucaoStorage {
    trait Service {
      def all: UIO[List[DespesaPublicaExecucao]]
    }

    val doobie: URLayer[DbTransactor, DespesasPublicasExecucaoStorage] =
      ZLayer.fromFunction[DbTransactor, DespesasPublicasExecucaoStorage.Service] { dbTransactor =>
        Doobie(dbTransactor.get.xa)
      }
  }

  def findAll: RIO[DespesasPublicasExecucaoStorage, List[DespesaPublicaExecucao]] =
    RIO.accessM(_.get.all)
}
