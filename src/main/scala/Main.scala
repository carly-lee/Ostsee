import zio._
import zio.http._

object Main extends ZIOAppDefault {
  val getTodos =
    Method.GET / "todos" -> handler(Response.text("a list of Todos"))

  val getTodo =
    Method.GET / "todo" / int("todoId") ->
      handler { (todoId: Int, req: Request) =>
        Response.text(s"Get one Todo: $todoId")
      }

  val createTodo =
    Method.POST / "todo" -> handler { (req: Request) => {
      Response(body = req.body)
    }}

  val updateTodo =
    Method.PUT / "todo" -> handler(Response.text("a todo is updated"))

  val deleteTodo =
    Method.DELETE / "todo" -> handler(Response.text("a todo is deleted"))

  val app = Routes(
    getTodos,
    getTodo,
    createTodo,
    updateTodo,
    deleteTodo
  ).toHttpApp

  override def run = Server.serve(app).provide(Server.default)
}

