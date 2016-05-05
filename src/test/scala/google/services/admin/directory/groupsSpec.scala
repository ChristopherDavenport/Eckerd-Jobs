package google.services.admin.directory

import org.scalatest.{FlatSpec, Matchers}
import com.google.api.services.admin.directory.DirectoryScopes
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.drive.DriveScopes
import com.google.api.services.gmail.GmailScopes
import com.typesafe.config.ConfigFactory
import com.google.api.services.admin.directory.model.Group

/**
  * Created by davenpcm on 5/5/16.
  */
class groupsSpec extends FlatSpec with Matchers {

  val config = ConfigFactory.load().getConfig("googleTest")
  val serviceAccountEmail = config.getString("email")
  val credentialFilePath = config.getString("pkcs12FilePath")
  val applicationName = config.getString("applicationName")
  val adminImpersonatedEmail = config.getString("impersonatedEmail")

  val ListScopes = List(
    DirectoryScopes.ADMIN_DIRECTORY_USER,
    DirectoryScopes.ADMIN_DIRECTORY_GROUP,
    DirectoryScopes.ADMIN_DIRECTORY_GROUP_MEMBER,
    CalendarScopes.CALENDAR,
    DriveScopes.DRIVE,
    DriveScopes.DRIVE_APPDATA,
    GmailScopes.GMAIL_COMPOSE
  )

  val service = google.services.Service(serviceAccountEmail,
    adminImpersonatedEmail,
    credentialFilePath,
    applicationName,
    ListScopes
  )

  val Directory = service.Directory

  val list = Directory.groups.list()


  "A Directory utilizing groups" should "return a List[Group]" in {
     list shouldBe a[List[Group]]
  }

}