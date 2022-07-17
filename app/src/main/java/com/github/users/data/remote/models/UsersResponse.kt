package com.github.users.data.remote.models

data class UsersResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)