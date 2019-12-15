package me.karun.gscr.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import me.karun.gscr.exceptions.InvalidInputException
import me.karun.gscr.models.TaskDescriptor

class YamlParserService {
  def convertYAMLToObject(string: String): TaskDescriptor = {
    if(string.isEmpty)
      throw new InvalidInputException("Empty string is an invalid input")
    var mapper = new ObjectMapper(new YAMLFactory())

    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(string, classOf[TaskDescriptor])
  }

}
