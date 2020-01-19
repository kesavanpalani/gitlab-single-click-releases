package me.karun.gscr.services

import java.nio.file.Path

import me.karun.gscr.Helper
import org.scalatest.{BeforeAndAfter, FunSuite}

class FileServiceTest extends FunSuite with BeforeAndAfter {

  test("should able to read the file and return the value in String format") {
    val fileService = new FileService()
    val sampleYamlString = Helper.getSampleYamlString

    assertResult(sampleYamlString) {
      fileService.read(getClass.getResource("/sample.yml").getPath)
    }
  }
}
