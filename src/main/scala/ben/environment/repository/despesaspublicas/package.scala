package ben.environment.repository

import ben.domain.DespesaPublicaExecucao
import zio._
import zio.macros.accessible

package object despesaspublicas {

  type DespesasPublicasExecucaoStorage = Has[DespesasPublicasExecucaoStorage.Service]

  @accessible
  object DespesasPublicasExecucaoStorage {
    trait Service {
      def all(): fs2.Stream[Task, DespesaPublicaExecucao]
      def byId(id: Long): Task[Option[DespesaPublicaExecucao]]
    }

    val doobie: URLayer[DbTransactor, DespesasPublicasExecucaoStorage] =
      ZLayer.fromFunction[DbTransactor, DespesasPublicasExecucaoStorage.Service] { dbTransactor =>
        Doobie(dbTransactor.get.xa)
      }
  }
}
