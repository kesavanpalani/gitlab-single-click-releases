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
    val sampleYamlString = Helper.getSampleYamlString
    val gitlabPipelineParametersList = Helper.createTaskTestData
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