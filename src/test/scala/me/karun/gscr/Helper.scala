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
}
