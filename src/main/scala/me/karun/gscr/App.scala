package me.karun.gscr

import me.karun.gscr.models.{TaskDescriptor, ClientCaller}
import me.karun.gscr.services.{FileService, YamlParserService}

object App {
  def main(args: Array[String]): Unit = {
    val fileService: FileService = new FileService
    val input: String = fileService.read(args(0))
    val token: String = args(1)
    val yamplParserService : YamlParserService = new YamlParserService
    val taskDescriptor: TaskDescriptor =  yamplParserService.convertYAMLToObject(input)
    taskDescriptor.print
    ClientCaller.execute(taskDescriptor.getTasksInOrder, token);
  }
}