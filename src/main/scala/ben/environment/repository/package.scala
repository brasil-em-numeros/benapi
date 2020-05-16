package ben.environment

import ben.environment.config.DbConfiguration
import doobie.util.transactor.Transactor
import zio._
import zio.interop.catz._

package object repository {

  type DbTransactor = Has[DbTransactor.Resource]

  object DbTransactor {
    trait Resource {
      val xa: Transactor[Task]
    }

    val live: URLayer[DbConfiguration, DbTransactor] =
      ZLayer.fromFunction { env =>
        val dbCfg = env.get

        new Resource {
          val xa: Transactor[Task] =
            Transactor.fromDriverManager(dbCfg.driver, dbCfg.url, dbCfg.user, dbCfg.password)
        }
      }
  }
}
