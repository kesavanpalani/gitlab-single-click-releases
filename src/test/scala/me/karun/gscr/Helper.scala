package me.karun.gscr

import me.karun.gscr.models.GitlabPipelineParameters

import scala.collection.immutable.Map

object Helper {
  def createGitlabPipelineParametersTestData: List[GitlabPipelineParameters] = {
    val gitlabPipelineParameters1: GitlabPipelineParameters = GitlabPipelineParameters("task-1", "marvel", "endgame", "master")
    val gitlabPipelineParameters2: GitlabPipelineParameters = GitlabPipelineParameters("task-2", "marvel", "infinity war", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val gitlabPipelineParameters3: GitlabPipelineParameters = GitlabPipelineParameters("task-3", "DC", "justice league", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    val gitlabPipelineParameters4: GitlabPipelineParameters = GitlabPipelineParameters("task-4", "marvel", "age of ultron", "master",
      Map[String, String]("DB_PASSWORD" -> "1234", "DB_USER_NAME" -> "k7"))
    List(gitlabPipelineParameters1, gitlabPipelineParameters2, gitlabPipelineParameters3, gitlabPipelineParameters4)
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
