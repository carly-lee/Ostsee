package endpoints

import zio.http.{Method, Request, Response, Route, handler, int}

object GetTodo {
  val getTodo: Route[Any, Nothing] =
    Method.GET / "todo" / int("todoId") ->
    handler { (todoId: Int, req: Request) =>
      Response.text(s"Get one Todo: $todoId")
    }
}
