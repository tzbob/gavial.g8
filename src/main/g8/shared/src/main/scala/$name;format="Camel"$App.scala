import mtfrp.core._
import UI.html.all._

object $name;format="Camel"$App extends GavialApp {
  val port = 8080
  val host = "localhost"

  val headExtensions = List(script(src := s"$name;format="norm"$-fastopt-bundle.js"))

  val ui = ClientDBehavior.constant {
    div(h1("Hello $name$!"),
        p("Enjoy Gavial!"))
  }
}
