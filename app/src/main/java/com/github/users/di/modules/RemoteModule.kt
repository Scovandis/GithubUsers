package com.github.users.di.modules

import com.github.users.data.remote.UsersInterface
import com.github.users.data.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
  @Singleton
  @Provides
  fun usersSearchApi(@Named("retrofit") retrofit: Retrofit): UsersInterface{
    return retrofit.create(UsersInterface::class.java)
  }

  @Singleton
  @Provides
  fun usersRepository(usersAPI: UsersInterface): UsersRepository{
    return UsersRepository(usersAPI)
  }
}