import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._


/** */
class MameSimulation extends Simulation {

  val httpProtocol = http
    .baseURL("http://106.186.29.62")
    .inferHtmlResources()
    .acceptHeader("application/json, text/plain, */*")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("ja,en-us;q=0.7,en;q=0.3")
    .connection("keep-alive")
    .contentTypeHeader("application/json;charset=utf-8")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:35.0) Gecko/20100101 Firefox/35.0")

  val headers_0 = Map("Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

  val headers_1 = Map("Pragma" -> "no-cache")

  val uri1 = "http://106.186.29.62/mameclub"

  val scn = scenario("RecordedSimulation")
    .repeat(1) {
    exec(http("request_0")
      .get("/mameclub/voters")
      .headers(headers_0))
      .pause(1)
      .exec(http("request_1")
      .post("/mameclub/vote")
      .check(status.is(200))
      .headers(headers_1)
      .body(RawFileBody("RecordedSimulation_0001_request.txt")))
      .pause(1)
      .exec(http("request_2")
      .post("/mameclub/vote")
      .check(status.is(200))
      .headers(headers_1)
      .body(RawFileBody("RecordedSimulation_0002_request.txt"))
      .resources(http("request_3")
      .post(uri1 + "/vote")
      .check(status.is(200))
      .headers(headers_1)
      .body(RawFileBody("RecordedSimulation_0003_request.txt")),
        http("request_4")
          .post(uri1 + "/vote")
          .check(status.is(200))
          .headers(headers_1)
          .body(RawFileBody("RecordedSimulation_0004_request.txt"))))
  }

  setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)

}
