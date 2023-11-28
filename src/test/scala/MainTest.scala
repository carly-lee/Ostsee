import zio.test._
import zio.test.Assertion.equalTo
import zio.http._

object MainTest extends ZIOSpecDefault {
  def spec = suite("http")(
    test("should be ok") {
      val app = Main.app
      val req = Request.get("todos")
      assertZIO(app.runZIO(req))(equalTo(Response.text("a list of Todos")))
    }
  )
}


