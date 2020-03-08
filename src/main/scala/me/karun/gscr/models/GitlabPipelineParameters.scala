package me.karun.gscr.models

import scala.collection.immutable.Map


<<<<<<< HEAD
case class GitlabPipelineParameters(id: String, groupName: String, projectName: String, runFor: String, inputVariable: Map[String, String] = null, orderToExecute: List[String]=null)
=======
case class GitlabPipelineParameters(id: String, groupName: String, projectName: String, runFor: String,
                                    inputVariable: Map[String, String] = null, orderToExecute: List[String]=null)
>>>>>>> upstream/master
