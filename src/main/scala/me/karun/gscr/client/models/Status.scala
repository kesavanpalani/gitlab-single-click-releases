package me.karun.gscr.client.models

final case class Status(icon: String, text: String, label: String, group: String, tooltip: String, has_details: Boolean,
                        details_path: String, illustration: String, favicon: String)
