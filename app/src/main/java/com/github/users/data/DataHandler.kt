package com.github.users.data

sealed class DataHandler{
  class Success<T>(var data: T?): DataHandler()
  class Failure(var message: String?): DataHandler()
}