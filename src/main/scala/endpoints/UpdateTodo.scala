package endpoints

import zio.http.{Method, Response, Route, handler}

object UpdateTodo {
  val updateTodo: Route[Any, Nothing] =
    Method.PUT / "todo" -> handler(Response.text("a todo is updated"))
}
