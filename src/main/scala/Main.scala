import zio._
import zio.http._

object Main extends ZIOAppDefault {
  val textRoute =
    Method.GET / "text" -> handler(Response.text("Hello World!"))

  val jsonRoute =
    Method.GET / "json" -> handler(Response.json("""{"greetings": "Hello World!"}"""))

  val app = Routes(textRoute, jsonRoute).toHttpApp

  override def run = Server.serve(app).provide(Server.default)
}

