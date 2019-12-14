package me.karun.gscr.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import me.karun.gscr.models.GitlabPipelineParameters
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import me.karun.gscr.exceptions.InvalidInputException

class YamlParserService {
  def convertYAMLToObject(string: String): Object = {
    if(string.isEmpty)
      throw new InvalidInputException("Empty string is an invalid input")

    var mapper = new ObjectMapper(new YAMLFactory())
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(string, classOf[GitlabPipelineParameters])
  }

}
