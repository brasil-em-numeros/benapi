package ben

import ben.environment.Environments.appEnvironment
import ben.http.Server
import zio._
import zio.console.putStrLn

object Main extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = {
    val program = for {
      _ <- Server.runServer//.provideLayer(appEnvironment)
    } yield 1

    program.provideLayer(appEnvironment)
//      .foldM(
//        e => putStrLn(s"Execution failed with: ${e.printStackTrace()}") *> ZIO.succeed(1),
//        _ => ZIO.succeed(0)
//      )
  }
}
