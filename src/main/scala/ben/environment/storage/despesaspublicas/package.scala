package ben.environment.storage

import ben.domain.despesaspublicas.Execucao
import zio._
import zio.macros.accessible

package object despesaspublicas {

  type ExecucaoStorage = Has[ExecucaoStorage.Service]

  @accessible
  object ExecucaoStorage {
    trait Service {
      def byId(id: Long): Task[Option[Execucao]]
      def byCodigo(codigo: Long): fs2.Stream[Task, Execucao]
    }

    val doobie: URLayer[DbTransactor, ExecucaoStorage] =
      ZLayer.fromFunction[DbTransactor, ExecucaoStorage.Service] { dbTransactor ⇒
        Doobie(dbTransactor.get.xa)
      }
  }
}
