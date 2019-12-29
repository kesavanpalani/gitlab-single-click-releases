package me.karun.gscr.models

import scala.collection.immutable.Map


case class GitlabPipelineParameters(id: String, groupName: String, projectName: String, runFor: String, inputVariable: Map[String, String] = null)