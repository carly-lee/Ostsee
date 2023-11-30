import endpoints.*
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault}
import zio.http.{HttpApp, Route, Routes, Server}

object Main extends ZIOAppDefault {
  val app: HttpApp[Any] = Routes(
    GetTodos.getTodos,
    GetTodo.getTodo,
    CreateTodo.createTodo,
    UpdateTodo.updateTodo,
    DeleteTodo.deleteTodo
    ).toHttpApp

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = Server.serve(app).provide(Server.default)
}