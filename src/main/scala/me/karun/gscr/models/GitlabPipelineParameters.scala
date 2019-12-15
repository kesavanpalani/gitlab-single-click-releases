package me.karun.gscr.models

import scala.collection.immutable.Map


class GitlabPipelineParameters() {
  var id : String = _
  var groupName : String= _
  var projectName : String= _
  var runFor : String = _
  var inputVariable: Map[String, String] =  _

  def this(id: String, groupName: String, projectName: String, runFor: String) {
    this()
    this.id = id
    this.groupName = groupName
    this.projectName = projectName
    this.runFor = runFor
  }

  def this(id: String, groupName: String, projectName: String, runFor: String, inputVariables: Map[String, String]) {
    this(id, groupName, projectName, runFor)
    this.inputVariable = inputVariables
  }

  def getId: String = this.id

  def getGroupName: String = this.groupName

  def getProjectName: String = this.projectName

  def getRunFor: String = this.runFor

  def getInputVariable: Map[String, String] = this.inputVariable

  def setId(id: String): Unit = {
    this.id = id
  }

  def setGroupName(groupName: String): Unit = {
    this.groupName = groupName
  }

  def setProjectName(projectName: String): Unit = {
    this.projectName = projectName
  }

  def setRunFor(runFor: String): Unit = {
    this.runFor = runFor
  }

  def setInputVariable(inputVariable: Map[String, String]): Unit = {
    this.inputVariable = inputVariable
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[GitlabPipelineParameters]

  override def equals(other: Any): Boolean = other match {
    case that: GitlabPipelineParameters =>
      (that canEqual this) &&
        inputVariable == that.inputVariable &&
        id == that.id &&
        groupName == that.groupName &&
        projectName == that.projectName &&
        runFor == that.runFor
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(inputVariable, id, groupName, projectName, runFor)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
