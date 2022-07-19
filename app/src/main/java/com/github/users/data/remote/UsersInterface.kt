package com.github.users.data.remote

import com.github.users.data.remote.models.Item
import com.github.users.data.remote.models.UsersResponse
import com.github.users.utils.BASE_URL
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersInterface {
  @GET("search/users")
  fun getUsersSearch(@Query("q") username: String): Observable<UsersResponse>

  companion object {

    var retrofitService: UsersInterface? = null

    //Create the RetrofitService instance using the retrofit.
    fun getInstance(): UsersInterface {

      if (retrofitService == null) {
        val retrofit = Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())

          //You need to tell Retrofit that you want to use RxJava 3
          .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
          .build()
        retrofitService = retrofit.create(UsersInterface::class.java)
      }
      return retrofitService!!
    }
  }
}