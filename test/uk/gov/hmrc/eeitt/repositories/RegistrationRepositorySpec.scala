package uk.gov.hmrc.eeitt.repositories

import org.scalatest.concurrent.{ IntegrationPatience, ScalaFutures }
import org.scalatest.{ BeforeAndAfterEach, Inspectors, LoneElement }
import org.specs2.matcher.ExceptionMatchers
import reactivemongo.core.errors.DatabaseException
import uk.gov.hmrc.eeitt.model.Registration
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.ExecutionContext.Implicits.global

class RegistrationRepositorySpec extends UnitSpec with ExceptionMatchers with RegistrationRepositorySupport with BeforeAndAfterEach with ScalaFutures with LoneElement with Inspectors with IntegrationPatience {

  override protected def beforeEach(): Unit = {
    await(regRepo.removeAll())
    awaitIndexCreation()
  }

  private val registration1: Registration = Registration("g1", "SE39EP", false, "12LT001", "", List("LT", "LL"))
  private val registration2: Registration = Registration("g2", "SE39EX", false, "12LT002", "", List("LT", "LL", "XL"))

  "query registrations with a group id" should {
    "produce registration with the given group id" in {
      insertRegistration(registration1)
      insertRegistration(registration2)
      await(regRepo.count) shouldBe 2
      await(regRepo.findRegistrations("g1")) map (_.groupId) should contain theSameElementsAs (List("g1"))
    }
  }

  "inserting more than one registration with the same group id" should {
    "result in an error" in {
      insertRegistration(registration1)
      intercept[DatabaseException](insertRegistration(registration1))
    }
  }

}
