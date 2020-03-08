package me.karun.gscr

import me.karun.gscr.models.Task

import scala.collection.immutable.Map

object Helper {
  def createTaskTestData: List[Task] = {
    val task1: Task = Task("task-1", "marvel", "endgame", "master")
    val task2: Task = Task("task-2", "marvel", "infinity war", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task3: Task = Task("task-3", "DC", "justice league", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val task4: Task = Task("task-4", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    List(task1, task2, task3, task4)
  }

  def getSampleYamlString: String = {
    "tasks:\n  -\n    " +
      "id: task-1\n    groupName: marvel\n    projectName: endgame\n    runFor: master\n\n  -\n    " +
      "id: task-2\n    groupName: marvel\n    projectName: infinity war\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-3\n    groupName: DC\n    projectName: justice league\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n  -\n    " +
      "id: task-4\n    groupName: marvel\n    projectName: age of ultron\n    runFor: master\n    " +
      "inputVariable:\n      DB_PASSWORD: 1234\n      DB_USER_NAME: k7\n\n" +
      "orderToExecute:\n  - task-1\n  - task-2, task-3\n  - task-4"
  }
}
