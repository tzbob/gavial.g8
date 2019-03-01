import mtfrp.core._
import UI.html.all._

object $name;format="Camel"$App extends GavianApp {
  val port = 8080
  val host = "localhost"

  val headExtensions = List(script(src := s"$name$-fastopt.js"))

  val ui = ClientDBehavior.constant {
    div(h1("Hello $name$!"),
        p("Enjoy Gavian!"))
  }
}
