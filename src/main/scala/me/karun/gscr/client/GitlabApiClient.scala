package me.karun.gscr.client

import me.karun.gscr.client.models.{Pipeline, PipelineTrigger}
import org.json4s.native.Serialization
import sttp.client._
import sttp.client.json4s._

class GitlabApiClient(gitlabUrl: String, gitlabToken: String)
                     (implicit backend: SttpBackend[Identity, Nothing, NothingT]) {
  implicit val serialization: Serialization.type = org.json4s.native.Serialization

  def getPipelineTriggers(projectId: String): Response[Either[ResponseError[Exception], List[PipelineTrigger]]] = {
    val response = basicRequest
      .header("PRIVATE-TOKEN", gitlabToken)
      .get(uri"$gitlabUrl/api/v4/projects/$projectId/triggers")
      .response(asJson[List[PipelineTrigger]])
      .send()
    response
  }

  def getPipeline(projectId: String, pipelineId: String): Response[Either[ResponseError[Exception], Pipeline]] = {
    val response = basicRequest
      .header("PRIVATE-TOKEN", gitlabToken)
      .get(uri"$gitlabUrl/api/v4/projects/$projectId/pipelines/$pipelineId")
      .response(asJson[Pipeline])
      .send()
    response
  }

  def triggerPipeline(projectId: String, pipeLineToken: String, branch: String): Response[Either[ResponseError[Exception], Pipeline]] = {
    val response = basicRequest
      .body(Map("token" -> pipeLineToken, "ref" -> branch))
      .post(uri"$gitlabUrl/api/v4/projects/$projectId/trigger/pipeline")
      .response(asJson[Pipeline])
      .send()
    response
  }

  def createTrigger(projectId: String): Response[Either[ResponseError[Exception], PipelineTrigger]] = {
    val response = basicRequest
      .header("PRIVATE-TOKEN", gitlabToken)
      .body(Map("description" -> "gxpp trigger"))
      .post(uri"$gitlabUrl/api/v4/projects/$projectId/triggers")
      .response(asJson[PipelineTrigger])
      .send()
    response
  }
}
