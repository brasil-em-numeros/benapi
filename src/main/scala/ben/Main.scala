package ben

import ben.environment.config.Configuration
import ben.http.Server
import zio._
import zio.console.putStrLn

object Main extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = {
    val program = for {
      server <- Server.runServer
    } yield server

    program
      .provideSomeLayer[ZEnv](Configuration.live)
      .foldM(
        e => putStrLn(s"Execution failed with: ${e.printStackTrace()}") *> ZIO.succeed(1),
        _ => ZIO.succeed(0)
      )
  }
}
