package com.github.users.data.repository

import com.github.users.data.remote.UsersInterface


class UsersRepository constructor(
  private val api: UsersInterface
) {
    fun getSearchUsers(username: String) = api.getUsersSearch(username)
}