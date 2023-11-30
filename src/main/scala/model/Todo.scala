package model

import constant.Priority
import io.circe.Json
import java.time.Instant

case class Todo(
                 title: String,
                 content: String = "",
                 isDone: Boolean = false,
                 priority: Priority = Priority.MEDIUM,
                 createdAt: String = Instant.now().toString
               )

object Todo:
  def deserialize(json: Json) = json.as[Todo](cursor => for {
    title <- cursor.downField("title").as[String]
    content <- cursor.downField("content").as[Option[String]]
    isDone <- cursor.downField("isDone").as[Option[Boolean]]
    priority <- cursor.downField("priority").as[Option[String]]
  } yield Todo(title = title,
               content = content.getOrElse(""),
               isDone = isDone.getOrElse(false),
               priority = Priority.valueOf(priority.getOrElse("MEDIUM"))))



