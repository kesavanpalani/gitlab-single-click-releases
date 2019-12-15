package me.karun.gscr.models

import java.util.NoSuchElementException

class TaskDescriptor() {
  private var tasks = List[GitlabPipelineParameters]()
  private var orderToExecute = List[String]()

  def this(tasks: List[GitlabPipelineParameters], orderToExecute: List[String]) {
    this()
    this.tasks = tasks
    this.orderToExecute = orderToExecute
  }

  def getTasks: List[GitlabPipelineParameters] = {
    this.tasks
  }

  def getOrderToExecute: List[String] = {
    this.orderToExecute
  }

  def setTasks(tasks: List[GitlabPipelineParameters]): Unit = {
    this.tasks = tasks
  }

  def setOrderToExecute(orderToExecute: List[String]): Unit = {
    this.orderToExecute = orderToExecute
  }

  def getTaskById(task_id: String) = {
    tasks.find(gitlabPipelineParameters => gitlabPipelineParameters.id == task_id).
      getOrElse(throw new NoSuchElementException("Task with %s is not found".format(task_id)))
  }

  def print(): Unit = {
    orderToExecute.foreach(taskId => println(taskId))
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[TaskDescriptor]

  override def equals(other: Any): Boolean = other match {
    case that: TaskDescriptor =>
      (that canEqual this) &&
        tasks == that.tasks &&
        orderToExecute == that.orderToExecute
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(tasks, orderToExecute)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
