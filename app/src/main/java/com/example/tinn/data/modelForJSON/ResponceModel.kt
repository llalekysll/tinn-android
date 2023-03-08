package com.example.tinn.data.modelForJSON

import com.example.tinn.data.emptities.user.*
import com.google.gson.annotations.SerializedName

data class ResponceModel <T>(
    val status: Boolean = false,
    //val errors: List<Any> = emptyList(),
    val data: T? = null
)

data class ResponceDataUserModel(
    val user: User = User(),
    @SerializedName("user_profiles")
    val userProfiles: UserProfiles = UserProfiles(),
    @SerializedName("user_notices")
    val userNotices: UserNotices = UserNotices(),
    @SerializedName("user_privacies")
    val userPrivacies: UserPrivacies = UserPrivacies(),
    val channel: Int? = null,
    //@SerializedName("user_subscriptions")
    //val userSubscriptions: Array<String> = arrayOf(),
    @SerializedName("profile_info")
    val profileInfo: ProfileInfo = ProfileInfo()
)