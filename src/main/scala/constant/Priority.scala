package constant

import zio.json.{DeriveJsonDecoder, DeriveJsonEncoder, JsonDecoder, JsonEncoder}

enum Priority(value: String) {
  case LOW extends Priority("LOW")
  case MEDIUM extends Priority("MEDIUM")
  case HIGH extends Priority("HIGH")
}


object Priority:
  given JsonEncoder[Priority] = DeriveJsonEncoder.gen[Priority]

  given JsonDecoder[Priority] = JsonDecoder.string.map {
    case "LOW" => Priority.LOW
    case "MEDIUM" => Priority.MEDIUM
    case "HIGH" => Priority.HIGH
    case _ => throw new IllegalArgumentException("Invalid priority value")
  }
