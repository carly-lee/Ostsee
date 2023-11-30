package endpoints

import zio.http.{Method, Response, Route, handler}

object DeleteTodo {
  val deleteTodo: Route[Any, Nothing] =
    Method.DELETE / "todo" -> handler(Response.text("a todo is deleted"))
}
