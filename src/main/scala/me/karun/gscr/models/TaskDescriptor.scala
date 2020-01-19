package me.karun.gscr.models

import java.util.NoSuchElementException

case class TaskDescriptor(tasks: List[GitlabPipelineParameters], orderToExecute: List[String]) {
  def getTaskById(task_id: String): GitlabPipelineParameters = {
    tasks.find(gitlabPipelineParameters => gitlabPipelineParameters.id == task_id).
      getOrElse(throw new NoSuchElementException("Task with %s is not found".format(task_id)))
  }

  def print(orderToExecute: List[String]): Unit = {
    orderToExecute.foreach(taskIds => {
      taskIterator(taskIds.split(","))
    })
  }

  private def taskIterator(tasks: Array[String]): Unit = {
    tasks.foreach(taskId => {
      val task = getTaskById(taskId.trim)
      println(task.id)
      if (isOrderToExecuteNotEmpty(task))
        print(task.orderToExecute)
    })
  }

  private def isOrderToExecuteNotEmpty(task: GitlabPipelineParameters) = {
    task.orderToExecute != null && task.orderToExecute.nonEmpty
  }
}
