package com.github.users.data.remote

import com.github.users.data.remote.models.UsersResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersInterface {
  @GET("search/users")
  fun getUsersSearch(@Query("q") username: String): Observable<UsersResponse>
}