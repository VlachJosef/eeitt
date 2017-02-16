package uk.gov.hmrc.eeitt.controllers

import play.api.Play.current
import play.api.libs.json.{JsObject, JsString, JsValue}
import play.api.libs.ws.{WS, WSResponse}
import uk.gov.hmrc.eeitt.MicroserviceShortLivedCache
import uk.gov.hmrc.http.cache.client.CacheMap
import uk.gov.hmrc.play.http.{HeaderCarrier, HttpResponse}
import uk.gov.hmrc.play.http.test.ResponseMatchers
import uk.gov.hmrc.play.http.ws.WSRequest
import uk.gov.hmrc.play.it.{ExternalService, ExternalServiceRunner, MicroServiceEmbeddedServer, ServiceSpec}
import uk.gov.hmrc.play.test.UnitSpec

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
  * Created by harrison on 14/02/17.
  */

