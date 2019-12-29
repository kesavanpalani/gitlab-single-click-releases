package me.karun.gscr.models

import java.util.NoSuchElementException

case class TaskDescriptor(tasks: List[GitlabPipelineParameters], orderToExecute: List[String]) {
  def getTaskById(task_id: String): GitlabPipelineParameters = {
    tasks.find(gitlabPipelineParameters => gitlabPipelineParameters.id == task_id).
      getOrElse(throw new NoSuchElementException("Task with %s is not found".format(task_id)))
  }
  def print(): Unit = {
    orderToExecute.foreach(taskId => println(taskId))
  }
}
