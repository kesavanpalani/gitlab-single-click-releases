package me.karun.gscr.models

import java.util.NoSuchElementException

import lombok.{Getter, Setter}

@Getter
@Setter
class TaskDescriptor(var gitlabPipelineParametersList: List[GitlabPipelineParameters], var orderToExecute: List[String]) {

  def getTaskById(task_id: String) = {
    gitlabPipelineParametersList.find(gitlabPipelineParameters => gitlabPipelineParameters.id == task_id).
      getOrElse(throw new NoSuchElementException("Task with %s is not found".format(task_id)))
  }

}
