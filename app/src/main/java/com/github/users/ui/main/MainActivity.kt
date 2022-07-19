package com.github.users.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.users.data.remote.UsersInterface
import com.github.users.data.remote.models.Item
import com.github.users.data.repository.UsersRepository
import com.github.users.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.row_users.view.*


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  lateinit var mainVM: MainViewModel
  lateinit var mainAdapter: MainAdapter
  private val retrofitService = UsersInterface.getInstance()

  private val TAG = MainActivity::class.java.simpleName
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    mainAdapter = MainAdapter()
    mainVM = ViewModelProvider(this, MainViewModelFactory(UsersRepository(retrofitService))).get(MainViewModel::class.java)
//    mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
//    mainVM.dataHandlerState.observe(this){
//      when(it){
//        is DataHandler.Success<*> -> {binding.progressCircular.visibility = View.GONE}
//        is DataHandler.Failure -> {binding.progressCircular.visibility = View.VISIBLE}
//      }
//    }
    mainVM.getSearchUsers("a")
    mainVM.usersList.observe(this) {
      Log.d(TAG, "DATA $it")
      mainAdapter.setData(it as MutableList<Item>)
    }

    binding.rvUser.apply {
      layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
      adapter = mainAdapter
    }
  }
}