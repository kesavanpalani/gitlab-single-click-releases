package me.karun.gscr.models

import scala.collection.immutable.Map


case class Task(id: String, groupName: String, projectName: String, projectId: String, runFor: String, inputVariable: Map[String, String] = null, orderToExecute: List[String]=null)