import cats.effect.{IO, IOApp}
import cats.effect.kernel.Ref
import cats.instances.list._
import scala.concurrent.duration.{FiniteDuration, SECONDS}
import cats.syntax.all._

object Main extends IOApp.Simple {

  def personalPrinter(aNumber: Int): IO[Unit]                  = IO.println(s"Extracting person ${aNumber}")
  def putString      (str: String) : IO[Unit]                  = IO.println(str)

  def extractionMethod(aNumber: Int, name: String, time: Int)(implicit ref: Ref[IO, List[String]]) : IO[Unit] = for {
    _ <- personalPrinter(aNumber)
    _ <- IO.sleep(FiniteDuration(time, SECONDS)) *> putString("Going to update the applicationRef")
    _ <- ref.update(a => a ++ List(name))        *> putString(s"ref update completed for $aNumber")
  } yield ()

  def firstExtraction(implicit ref: Ref[IO, List[String]])   = extractionMethod(1, "Ben", 1)
  def secondExtraction(implicit ref: Ref[IO, List[String]])  = extractionMethod(2, "Sam", 2)
  def lastExtraction(implicit ref: Ref[IO, List[String]])    = extractionMethod(3, "chris", 3)

  def masterExtractionMethod: IO[Unit] = {
    Ref.of[IO, List[String]](List.empty[String]).flatMap(implicit ref => {
     val listOfExtractions =  List(firstExtraction, secondExtraction, lastExtraction).parSequence.void
      listOfExtractions *> ref.get.flatMap(IO.println(_))
    })
  }

  def run: IO[Unit] = masterExtractionMethod

}
