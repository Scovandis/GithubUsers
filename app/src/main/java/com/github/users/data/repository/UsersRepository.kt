package com.github.users.data.repository

import com.github.users.data.remote.UsersInterface
import com.github.users.data.remote.models.UsersResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersRepository @Inject constructor(
  private val api: UsersInterface
) {
  fun getSearchUsers(username: String): Observable<UsersResponse> {
    return api.getUsersSearch(username)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }
}