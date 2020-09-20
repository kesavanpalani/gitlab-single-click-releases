package me.karun.gscr.models

import me.karun.gscr.client.GitlabApiClient
import me.karun.gscr.client.models.{Pipeline, PipelineTrigger}
import me.karun.gscr.exceptions.GitlabApiClientException
import sttp.client.{HttpURLConnectionBackend, Identity, NothingT, SttpBackend}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

object ClientCaller {
  val gitlabUrl = "https://gitlab.com"
  var userToken: String = _
  implicit val backend: SttpBackend[Identity, Nothing, NothingT] = HttpURLConnectionBackend()
  val apiClient: GitlabApiClient = new GitlabApiClient(gitlabUrl, userToken)

  private def triggerTasks(listBuffer: ListBuffer[Task]): mutable.HashMap[String, Long] = {
    val taskIdPipelineIdMapper: mutable.HashMap[String, Long] = new mutable.HashMap[String, Long]
    listBuffer.foreach(task => {
      val pipeLineTriggersResponse = apiClient.getPipelineTriggers(task.projectId)
      if (pipeLineTriggersResponse.isSuccess) {
        val pipeLineTriggers: List[PipelineTrigger] = pipeLineTriggersResponse.body.getOrElse(or = List())
        if (pipeLineTriggers.nonEmpty) {
          val pipelineTrigger: PipelineTrigger = apiClient.createTrigger(task.projectId).body.getOrElse(or =
            throw new GitlabApiClientException("Unable to create trigger for the task with project id: ".concat(task.projectId)))
          val pipeLine: Pipeline = getPipeline(task, pipelineTrigger)
          taskIdPipelineIdMapper.put(task.id, pipeLine.id)
        } else {
          val pipeLine: Pipeline = getPipeline(task, pipeLineTriggers.last)
          taskIdPipelineIdMapper.put(task.id, pipeLine.id)
        }
      } else {
        throw new GitlabApiClientException("Unable to fetch pipeline triggers for the task with project id: ".concat(task.projectId))
      }
    })
    taskIdPipelineIdMapper
  }


  private def getPipeline(task: Task, pipelineTrigger: PipelineTrigger) = {
    val pipeLine: Pipeline = apiClient.triggerPipeline(task.projectId, pipelineTrigger.token, task.runFor).body
      .getOrElse(or = throw new GitlabApiClientException("Pipeline trigger operation is failed for the project with id: ".concat(task.id)))
    println("Pipeline is triggered for the project id " + task.projectId)
    pipeLine
  }

  def checkStatus(tasks: ListBuffer[Task], taskIdPipelineIdMapper: mutable.HashMap[String, Long]): Boolean = {
    val isAllGreen = tasks.map(task =>
      apiClient.getPipeline(task.projectId, taskIdPipelineIdMapper(task.id).toString).body.getOrElse(null)
    ).filter(pipeline => pipeline != null)
      .count(pipeline => pipeline.status.equals("success")) > 0
    if (!isAllGreen) {
      checkStatus(tasks, taskIdPipelineIdMapper)
    }
    isAllGreen
  }

  def execute(groupedTasks: ListBuffer[ListBuffer[Task]], userToken: String): Unit = {
    this.userToken = userToken
    for (tasks <- groupedTasks) {
      val taskIdPipelineIdMapper = triggerTasks(tasks)
      breakable {
        while (true) {
          if (checkStatus(tasks, taskIdPipelineIdMapper)) {
            break
          }
        }
      }
    }
  }
}