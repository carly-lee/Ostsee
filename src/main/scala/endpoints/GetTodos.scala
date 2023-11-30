package endpoints

import zio.http.{Method, Response, Route, handler}

object GetTodos {
  val getTodos: Route[Any, Nothing] =
    Method.GET / "todos" -> handler(Response.text("a list of Todos"))
}
