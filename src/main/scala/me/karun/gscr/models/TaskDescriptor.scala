package me.karun.gscr.models

import java.util.NoSuchElementException
import scala.collection.mutable.ListBuffer

case class TaskDescriptor(tasks: List[Task], orderToExecute: List[String]) {
  private var tasksInOrder = new ListBuffer[ListBuffer[Task]]

  def getTasksInOrder: ListBuffer[ListBuffer[Task]] = {
    tasksInOrder = new ListBuffer[ListBuffer[Task]]
    arrangeTaskWithRespectToOrderToExecute(this.orderToExecute)
    tasksInOrder
  }

  def getTaskById(task_id: String): Task = {
    tasks.find(gitlabPipelineParameters => gitlabPipelineParameters.id == task_id).
      getOrElse(throw new NoSuchElementException("Task with id %s is not found".format(task_id)))
  }

  def print: Unit = {
    var i = 1
    getTasksInOrder.foreach(tasks => {
      println("-------------------- " + " Level-" + i + " --------------------")
      tasks.foreach(task => {
        printf(task.id + " ")
      })
      println()
      i = i + 1
    })
  }

  private def arrangeTaskWithRespectToOrderToExecute(orderToExecute: List[String]): Unit = {
    orderToExecute.foreach(taskIds => {
      var tasksForCurrentIteration = new ListBuffer[Task]
      taskIds.split(",").foreach(taskId => {
        val task = getTaskById(taskId.trim)
        tasksForCurrentIteration += task
        if (isOrderToExecuteNotEmpty(task))
          arrangeTaskWithRespectToOrderToExecute(task.orderToExecute)
      })
      tasksInOrder += tasksForCurrentIteration
    })
  }

  private def isOrderToExecuteNotEmpty(task: Task) = {
    task.orderToExecute != null && task.orderToExecute.nonEmpty
  }

}
