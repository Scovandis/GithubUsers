package com.github.users.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.users.data.remote.models.Item
import com.github.users.data.remote.models.UsersResponse
import com.github.users.data.repository.UsersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainViewModel (private val repository: UsersRepository): ViewModel(){
  val usersList = MutableLiveData<List<Item>>()
  val errorMessage = MutableLiveData<String>()
  lateinit var disposable: Disposable


  fun getSearchUsers(username: String) {

    // observer subscribing to observable
    val response = repository.getSearchUsers(username)
    response.subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(getMoviesListObserver())
  }

  private fun getMoviesListObserver(): Observer<UsersResponse> {
    return object : Observer<UsersResponse> {
      override fun onComplete() {
        //hide progress indicator .
      }

      override fun onError(e: Throwable) {
        usersList.postValue(null)

      }

      override fun onNext(t: UsersResponse) {
        usersList.postValue(t.items)
      }

      override fun onSubscribe(d: Disposable) {
        disposable = d
        //start showing progress indicator.
      }
    }
  }

}