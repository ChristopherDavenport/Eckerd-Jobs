package google.services.admin.directory

import com.google.api.services.admin.directory.model.UserPhoto
import google.services.JavaConverters._
import scala.util.{Failure, Success, Try}

/**
  * Created by davenpcm on 5/3/16.
  */
class photos(directory: Directory) {
  private val service = directory.asJava

  def get(userKey: String): Either[Throwable, UserPhoto] = {

    val returnType = Try( service.users().photos().get(userKey).execute() )

    returnType match {
      case Success(value) => Right(value)
      case Failure(exception) => Left(exception)
    }
  }

}
