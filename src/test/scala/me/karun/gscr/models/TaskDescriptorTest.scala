package me.karun.gscr.models

import java.util.NoSuchElementException

import me.karun.gscr.Helper
import org.scalatest.FunSuite

import scala.collection.immutable.Map
import scala.collection.mutable.ListBuffer

class TaskDescriptorTest extends FunSuite {

  test("should able to create task descriptor object") {
    val gitlabPipelineParametersList = Helper.createTaskTestData
    val orderToExecute = List("task-1", "task-2, task-4", "task-3")
    val taskDescriptor: TaskDescriptor = TaskDescriptor(gitlabPipelineParametersList, orderToExecute)

    assertResult(taskDescriptor.tasks) {
      gitlabPipelineParametersList
    }
  }

  test("should able to retrieve the gitlab pipeline parameter for a given task id") {
    val tasks = Helper.createTaskTestData
    val orderToExecute = List("task-1", "task-2, task-4", "task-3")
    val taskDescriptor: TaskDescriptor = TaskDescriptor(tasks, orderToExecute)

    assertResult(tasks(3)) {
      taskDescriptor.getTaskById("task-4")
    }
  }

  test("get task by id should throw error if task is not found") {
    val gitlabPipelineParametersList = List()
    val orderToExecute = List()
    val taskDescriptor: TaskDescriptor = TaskDescriptor(gitlabPipelineParametersList, orderToExecute)

    assertThrows[NoSuchElementException] {
      taskDescriptor.getTaskById("task-2")
    }
  }

  test ("get all the tasks in particular order") {
    val task1: Task = Task("task-1", "marvel", "endgame", "master")
    val task2: Task = Task("task-2", "marvel", "infinity war", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task3: Task = Task("task-3", "DC", "justice league", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task4: Task = Task("task-4", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task5: Task = Task("task-5", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task6: Task = Task("task-6", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task7: Task = Task("task-7", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))

    val subTask1 = Task("subtask-1","","","",null, List("task-2", "task-4, task-5", "task-6"))
    val taskDescriptor = TaskDescriptor(List(task1, task2, task3,task4,task5,task6,task7, subTask1), List("task-1",
      "subtask-1, task-3", "task-7"))
    val expectedOutput = ListBuffer(ListBuffer(task1), ListBuffer(task2), ListBuffer(task4, task5), ListBuffer(task6),
      ListBuffer(subTask1, task3), ListBuffer(task7))

    assertResult(expectedOutput){
      taskDescriptor.getTasksInOrder
    }
  }
}
