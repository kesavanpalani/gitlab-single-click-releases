package me.karun.gscr.models

import lombok.{Getter, Setter}

import scala.collection.immutable.Map

@Getter
@Setter
class GitlabPipelineParameters(var id: String, var groupName: String, var projectName: String, var runFor: String) {
  var inputVariables=  Map[String, String]()

  def this(id: String, groupName: String, projectName: String, runFor: String, inputVariables: Map[String, String]) {
    this(id, groupName, projectName, runFor)
    this.inputVariables = inputVariables
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[GitlabPipelineParameters]

  override def equals(other: Any): Boolean = other match {
    case that: GitlabPipelineParameters =>
      (that canEqual this) &&
        inputVariables == that.inputVariables &&
        id == that.id &&
        groupName == that.groupName &&
        projectName == that.projectName &&
        runFor == that.runFor
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(inputVariables, id, groupName, projectName, runFor)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
