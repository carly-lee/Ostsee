package endpoints

import zio.http.{Method, Request, Response, Route, Status, handler}
import io.circe.parser.parse
import model.Todo
import zio.ZIO

object CreateTodo {
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
}
