package me.karun.gscr.models

import java.util.NoSuchElementException

import me.karun.gscr.Helper
import org.scalatest.FunSuite

class TaskDescriptorTest extends FunSuite {

  test("should able to create task descriptor object") {
    val gitlabPipelineParametersList = Helper.createGitlabPipelineParametersTestData
    val orderToExecute = List("task-1", "task-2, task-4", "task-3")
    val taskDescriptor: TaskDescriptor = TaskDescriptor(gitlabPipelineParametersList, orderToExecute)

    assertResult(taskDescriptor.tasks) {
      gitlabPipelineParametersList
    }
  }

  test("should able to retrieve the gitlab pipeline parameter for a given task id") {
    val tasks = Helper.createGitlabPipelineParametersTestData
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
}
