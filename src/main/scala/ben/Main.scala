package ben

import ben.environment.Environments.appEnvironment
import ben.http.Server
import zio._

object Main extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = {
    val program = for {
      _ ← Server.runServer
    } yield 1

    program.provideLayer(appEnvironment)
  }
}
