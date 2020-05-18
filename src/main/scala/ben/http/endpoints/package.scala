package ben.http

import cats.Applicative
import io.circe.Encoder
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

package object endpoints {

  implicit def apJsonEntityEncoder[F[_]: Applicative, A](implicit encoder: Encoder[A]): EntityEncoder[F, A] =
    jsonEncoderOf[F, A]

  implicit def streamEntityEncoder[F[_]: Applicative, A](implicit entityEncoder: EntityEncoder[F, A]): EntityEncoder[F, fs2.Stream[F, A]] =
    EntityEncoder.streamEncoder[F, A]
}