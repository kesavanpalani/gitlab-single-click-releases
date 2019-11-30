package me.karun.gscr.models

import lombok.{Getter, Setter}

import scala.collection.immutable.Map

@Getter
@Setter
class GitlabPipelineParameters(var groupName: String, var projectName: String, var runFor: String) {
  var inputVariables=  Map[String, String]()

  def this() {
    this("", "", "")
  }

  def this(groupName: String, projectName: String, runFor: String, inputVariables: Map[String, String]) {
    this(groupName, projectName, runFor)
    this.inputVariables = inputVariables
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[GitlabPipelineParameters]

  override def equals(other: Any): Boolean = other match {
    case that: GitlabPipelineParameters =>
      (that canEqual this) &&
        inputVariables == that.inputVariables &&
        groupName == that.groupName &&
        projectName == that.projectName &&
        runFor == that.runFor
    case _ => false
  }

}
