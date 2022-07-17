package com.github.users.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.users.R
import com.github.users.data.DataHandler
import com.github.users.data.remote.models.Item
import com.github.users.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.row_users.view.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val mainVM : MainViewModel by viewModels()
  lateinit var mainAdapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    mainAdapter = MainAdapter()

    mainVM.dataHandlerState.observe(this){
      when(it){
        is DataHandler.Success<*> -> {binding.progressCircular.visibility = View.GONE}
        is DataHandler.Failure -> {binding.progressCircular.visibility = View.VISIBLE}
      }
    }
    binding.etSearch.addTextChangedListener(object: TextWatcher{
      override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


      }

      override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

      }

      override fun afterTextChanged(p0: Editable?) {
        if (p0!!.isEmpty()){
          mainVM.getUsers("a")
        }else{
          mainVM.getUsers(binding.etSearch.text.toString())
        }
      }
    })

    mainVM.currentUsers.observe(this) {
      mainAdapter.setData(it as ArrayList<Item> /* = java.util.ArrayList<com.github.users.data.remote.models.Item> */)
    }

    binding.rvUser.apply {
      layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
      adapter = mainAdapter
    }
  }
}