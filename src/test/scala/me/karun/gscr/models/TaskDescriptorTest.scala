package me.karun.gscr.models

import java.util.NoSuchElementException

import org.scalatest.FunSuite
import me.karun.gscr.models.TaskDescriptor

class TaskDescriptorTest extends FunSuite {
  test("should able to create task descriptor object") {
    val gitlabPipelineParameters1 : GitlabPipelineParameters = new GitlabPipelineParameters("task-1","Avengers", "endgame", "master")
    val gitlabPipelineParameters2 : GitlabPipelineParameters = new GitlabPipelineParameters("task-2","Avengers", "age of ultron", "master")
    val gitlabPipelineParameters3 : GitlabPipelineParameters = new GitlabPipelineParameters("task-3","Avengers", "infinity war", "master")
    val gitlabPipelineParameters4 : GitlabPipelineParameters = new GitlabPipelineParameters("task-4","Avengers", "assemble", "master")

    val gitlabPipelineParametersList = List(gitlabPipelineParameters1, gitlabPipelineParameters2, gitlabPipelineParameters3, gitlabPipelineParameters4)

    val orderToExecute = List("task-1", "task-2, task-4", "task-3")
    val taskDescriptor: TaskDescriptor = new TaskDescriptor(gitlabPipelineParametersList, orderToExecute)
    assertResult(taskDescriptor.gitlabPipelineParametersList) {
      gitlabPipelineParametersList
    }
  }

  test("should able to retrieve the gitlab pipeline parameter for a given task id") {
    val gitlabPipelineParameters1 : GitlabPipelineParameters = new GitlabPipelineParameters("task-1","Avengers", "endgame", "master")
    val gitlabPipelineParameters2 : GitlabPipelineParameters = new GitlabPipelineParameters("task-2","Avengers", "age of ultron", "master")
    val gitlabPipelineParameters3 : GitlabPipelineParameters = new GitlabPipelineParameters("task-3","Avengers", "infinity war", "master")
    val gitlabPipelineParameters4 : GitlabPipelineParameters = new GitlabPipelineParameters("task-4","Avengers", "assemble", "master")

    val gitlabPipelineParametersList = List(gitlabPipelineParameters1, gitlabPipelineParameters2, gitlabPipelineParameters3, gitlabPipelineParameters4)

    val orderToExecute = List("task-1", "task-2, task-4", "task-3")
    val taskDescriptor: TaskDescriptor = new TaskDescriptor(gitlabPipelineParametersList, orderToExecute)
    assertResult(gitlabPipelineParameters4){
      taskDescriptor.getTaskById("task-4")
    }
  }

  test("get task by id should throw error if task is not found") {
    val gitlabPipelineParametersList = List()
    val orderToExecute = List()
    val taskDescriptor: TaskDescriptor = new TaskDescriptor(gitlabPipelineParametersList, orderToExecute)

    assertThrows[NoSuchElementException] {
      taskDescriptor.getTaskById("task-2")
    }
  }
}
