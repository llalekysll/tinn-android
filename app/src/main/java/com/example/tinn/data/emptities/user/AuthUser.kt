package com.example.tinn.data.emptities.user

import java.util.*

data class AuthUser(
    val id: Int = 0,
    val email: String = "",
    val email_verified_at: String? = null,
    val end_registration: String? = null,
    val vk_token: String? = null,
    val vk_refresh_token: String? = null,
    val vk_id: String? = null,
    val google_id: String? = null,
    val google_token: String? = null,
    val google_refresh_token: String? = null,
    val role: String? = null,
    val delete_reason: String? = null,
    val delete_reason_text: String? = null,
    val created_at: Date = Date(),
    val updated_at: Date = Date()
)
