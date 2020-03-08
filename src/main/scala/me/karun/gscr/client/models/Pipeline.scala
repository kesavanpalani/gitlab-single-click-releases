package me.karun.gscr.client.models


final case class Pipeline(id: Long, sha: String, ref: String, status: String, created_at: String, updated_at: String,
                          web_url: String, before_sha: String, tag: Boolean, yaml_errors: String, user: User,
                          started_at: String, finished_at: String, committed_at: String, duration: String,
                          coverage: String, detailed_status: Status)

