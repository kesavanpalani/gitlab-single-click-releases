package me.karun.gscr.client.models

final case class PipelineTrigger(id: String, token: String, description: String, created_at: String, updated_at: String,
                                 last_used: String, owner: User)
