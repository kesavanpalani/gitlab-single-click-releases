package me.karun.gscr.services

import java.nio.file.{Files, Paths}

class FileService() {
  def read(fileName: String): String = {
    var content = new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8");
    content
  }

}
