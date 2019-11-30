package me.karun.gscr.services

import me.karun.gscr.exceptions.InvalidInputException
import me.karun.gscr.models.GitlabPipelineParameters
import org.scalatest.{BeforeAndAfter, FunSuite}

import scala.collection.immutable.Map

class YamlParserServiceTest extends FunSuite  with BeforeAndAfter {
  var yamlParserService: YamlParserService = _

  before {
    yamlParserService = new YamlParserService
  }
  test("should able to generate the gitlab pipeline params object for valid yaml string"){
    var sampleYamlString = "groupName : Avengers\nprojectName : endgame\nrunFor : master"
    var gitlabPipelineParameters: GitlabPipelineParameters = new GitlabPipelineParameters("Avengers", "endgame", "master")
    assertResult(gitlabPipelineParameters) {
      yamlParserService.convertYAMLToObject(sampleYamlString)
    }
  }

  test("should throw error if input is empty") {
    var sampleYamlString = ""
    assertThrows[InvalidInputException] {
      yamlParserService.convertYAMLToObject(sampleYamlString)
    }
  }

  test("should able to generate the gitlab pipeline params object for valid yaml string which contains the map of input variables") {
    var sampleYamlString = "groupName: Avengers\nprojectName: endgame\nrunFor: master\ninputVariables:\n DB_PASSWORD: 1234\n DB_USER_NAME: k7"
    var gitlabPipelineParameters: GitlabPipelineParameters = new GitlabPipelineParameters("Avengers", "endgame", "master", Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))

    assertResult(gitlabPipelineParameters) {
      yamlParserService.convertYAMLToObject(sampleYamlString)
    }
  }
}
