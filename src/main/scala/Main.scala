import model.Todo
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}
import zio.http.{HttpApp, Method, Request, Response, Route, Routes, Server, Status, handler, int}
import io.circe.parser.parse

object Main extends ZIOAppDefault {

  val getTodos: Route[Any, Nothing] =
    Method.GET / "todos" -> handler(Response.text("a list of Todos"))

  val getTodo: Route[Any, Nothing] =
    Method.GET / "todo" / int("todoId") ->
    handler { (todoId: Int, req: Request) =>
      Response.text(s"Get one Todo: $todoId")
    }

  val createTodo: Route[Any, Nothing] =
    Method.POST / "todo" -> handler { (req: Request) =>
      (for {
        json <- req.body.asString.map(parse)
        response <- json match {
          case Left(e) =>
            ZIO.debug(s"Failed to parse the input: $e")
               .as(Response.text(e.message).status(Status.BadRequest))
          case Right(json) => Todo.deserialize(json) match
            case Left(e) => ZIO.debug(s"Failed to parse the input: $e")
                               .as(Response.text(e.message).status(Status.BadRequest))
            case Right(todo) => ZIO.succeed(Response.text(s"A todo is created!: ${todo.toString}"))
        }
      } yield response).orDie
    }

  val updateTodo: Route[Any, Nothing] =
    Method.PUT / "todo" -> handler(Response.text("a todo is updated"))

  val deleteTodo: Route[Any, Nothing] =
    Method.DELETE / "todo" -> handler(Response.text("a todo is deleted"))

  val app: HttpApp[Any] = Routes(
    getTodos,
    getTodo,
    createTodo,
    updateTodo,
    deleteTodo
    ).toHttpApp

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = Server.serve(app).provide(Server.default)
}