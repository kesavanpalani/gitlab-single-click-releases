package me.karun.gscr.services

import me.karun.gscr.Helper
import me.karun.gscr.exceptions.InvalidInputException
import me.karun.gscr.models.TaskDescriptor
import org.scalatest.{BeforeAndAfter, FunSuite}

class YamlParserServiceTest extends FunSuite  with BeforeAndAfter {
  var yamlParserService: YamlParserService = _

  before {
    yamlParserService = new YamlParserService
  }
  test("should able to generate the gitlab pipeline params object for valid yaml string"){
    val sampleYamlString = "tasks:\n  -\n    " +
      "id: task-1\n    groupName: marvel\n    projectName: endgame\n    runFor: master\n\n  -\n    " +
      "id: task-2\n    groupName: marvel\n    projectName: infinity war\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-3\n    groupName: DC\n    projectName: justice league\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-4\n    groupName: marvel\n    projectName: age of ultron\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n" +
      "orderToExecute:\n  - task-1\n  - task-2, task-3\n  - task-4"
    val gitlabPipelineParametersList = Helper.createGitlabPipelineParametersTestData
    val orderToExecute = List("task-1", "task-2, task-3", "task-4")
    val taskDescriptor: TaskDescriptor = TaskDescriptor(gitlabPipelineParametersList, orderToExecute)

    assertResult(taskDescriptor) {
      yamlParserService.convertYAMLToObject(sampleYamlString)
    }
  }

  test("yaml parser service should throw error if input string is empty") {
    assertThrows[InvalidInputException] {
      yamlParserService.convertYAMLToObject("")
    }
  }
}
