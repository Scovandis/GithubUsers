package com.github.users.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.users.data.DataHandler
import com.github.users.data.remote.models.UsersResponse
import com.github.users.data.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: UsersRepository): ViewModel(){
  private val _getDataHandlerState: MutableLiveData<DataHandler> = MutableLiveData()
  val dataHandlerState: LiveData<DataHandler> = _getDataHandlerState

  private val _getCurrentUsers: MutableLiveData<MutableList<UsersResponse>> = MutableLiveData()
  val currentUsers: LiveData<MutableList<UsersResponse>> = _getCurrentUsers

  @SuppressLint("CheckResult")
  fun getUsers(username: String){
    repository.getSearchUsers(username)
      .subscribe({_getDataHandlerState.value = DataHandler.Success(it)},{_getDataHandlerState.value = DataHandler.Failure(it.message)})
  }
}