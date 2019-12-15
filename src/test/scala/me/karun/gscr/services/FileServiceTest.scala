package me.karun.gscr.services

import org.scalatest.{BeforeAndAfter, FunSuite}

class FileServiceTest extends FunSuite with BeforeAndAfter {

  test("should able to read the file and return the value in String format") {
    val fileService = new FileService()
    val sampleYamlString = "tasks:\n  -\n    " +
      "id: task-1\n    groupName: marvel\n    projectName: endgame\n    runFor: master\n\n  -\n    " +
      "id: task-2\n    groupName: marvel\n    projectName: infinity war\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-3\n    groupName: DC\n    projectName: justice league\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-4\n    groupName: marvel\n    projectName: age of ultron\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n" +
      "orderToExecute:\n  - task-1\n  - task-2, task-3\n  - task-4"

    assertResult(sampleYamlString) {
      fileService.read("/Users/kesavanp/open-source/gitlab-single-click-releases/sample.yml")
    }
  }

}
