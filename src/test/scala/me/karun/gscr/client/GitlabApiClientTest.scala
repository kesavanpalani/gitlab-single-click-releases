package me.karun.gscr.client

import me.karun.gscr.client.models.{Pipeline, PipelineTrigger}
import org.json4s._
import org.json4s.native.Serialization
import org.scalatest.{BeforeAndAfter, FunSuite}
import sttp.client.testing.SttpBackendStub
import sttp.client.{Identity, Response}
import sttp.model.{Method, StatusCode}

class GitlabApiClientTest extends FunSuite with BeforeAndAfter {

  val gitlabUrl = "http://gitlab.example.com"
  val gitlabToken = "fake-token"
  var apiClient: GitlabApiClient = _
  val pipelineStatus: String = getFileContent("src/test/resources/pipeline_response.json")
  val pipelineTrigger: String = getFileContent("src/test/resources/pipeline_trigger.json")
  val pipelineTriggerList: String = getFileContent("src/test/resources/pipeline_trigger_list.json")

  private def getFileContent(fileName: String): String = {
    val file = scala.io.Source.fromFile(fileName)
    val content = file.getLines().mkString
    file.close()
    content
  }

  implicit val backend: SttpBackendStub[Identity, Nothing] = SttpBackendStub.synchronous
    .whenRequestMatchesPartial({
      case request if request.uri.toString().contains("pipelines")
        && (request.headers.exists(header => header.name.equals("PRIVATE-TOKEN") && header.value.length > 0)
        || request.body.toString.contains("token")) => {
        Response.ok(pipelineStatus)
      }
      case request if request.uri.toString().equals("http://gitlab.example.com/api/v4/projects/1/triggers")
        && request.method.equals(Method.GET)
        && request.headers.exists(header => header.name.equals("PRIVATE-TOKEN") && header.value.length > 0) => {
        Response.ok(pipelineTriggerList)
      }
      case request if request.uri.toString().equals("http://gitlab.example.com/api/v4/projects/1/triggers")
        && request.method.equals(Method.POST)
        && request.headers.exists(header => header.name.equals("PRIVATE-TOKEN") && header.value.length > 0) => {
        Response.ok(pipelineTrigger)
      }
      case request if true => {
        println(request)
        Response.apply("Not Found", StatusCode.NotFound)
      }
    })

  var expectedPipeline: Pipeline = _
  var expectedPipelineTrigger: PipelineTrigger = _
  var expectedPipelineTriggerList: List[PipelineTrigger] = _

  before {
    apiClient = new GitlabApiClient(gitlabUrl, gitlabToken)
    implicit val formats = DefaultFormats
    expectedPipeline = Serialization.read[Pipeline](pipelineStatus)
    expectedPipelineTrigger = Serialization.read[PipelineTrigger](pipelineTrigger)
    expectedPipelineTriggerList = Serialization.read[List[PipelineTrigger]](pipelineTriggerList)

  }

  test("should get pipeline status") {
    val result = apiClient.getPipeline("1", "12")
    assert(result.body.isRight)
    assertResult(expectedPipeline) {
      result.body.getOrElse(throw new Exception("No Pipeline found"))
    }
  }

  test("should trigger the pipeline with given token") {
    val result = apiClient.triggerPipeline("1", "12")
    assert(result.body.isRight)
    assertResult(expectedPipeline) {
      result.body.getOrElse(throw new Exception("No Pipeline found"))
    }
  }

  test("should get all triggers available on the project") {
    val result = apiClient.getPipelineTriggers("1")
    assert(result.body.isRight)
    assertResult(expectedPipelineTriggerList) {
      result.body.getOrElse(throw new Exception("No PipelineTrigger found"))
    }
  }

  test("should create a trigger on the project") {
    val result = apiClient.createTrigger("1")
    assert(result.body.isRight)
    assertResult(expectedPipelineTrigger) {
      result.body.getOrElse(throw new Exception("No PipelineTrigger found"))
    }
  }
}
