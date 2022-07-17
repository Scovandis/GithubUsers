package com.github.users.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.users.data.remote.models.Item
import com.github.users.databinding.RowUsersBinding
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter () : RecyclerView.Adapter<MainAdapter.ViewHolder> (){
  var list = ArrayList<Item>()
  fun setData(lists: ArrayList<Item>){
    this.list = lists
  }
  inner class ViewHolder(private val binding: RowUsersBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(data: Item){
      with(binding){
        tvUsername.text = data.login
        tvFollowing.text = data.following_url
        Glide.with(binding.root.context).load(data.avatar_url).into(imgSrcBgGender)
        imvRandomColor.background = getRandomDrawbleColor()
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(binding)
  }

  private var isSelected = false

  fun isSelected(): Boolean {
    return isSelected
  }

  fun setSelected(selected: Boolean) {
    isSelected = selected
  }
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])
  }

  override fun getItemCount()= list.size
  private val vibrantLightColorList = arrayOf(
    ColorDrawable(Color.parseColor("#9ACCCD")), ColorDrawable(Color.parseColor("#8FD8A0")),
    ColorDrawable(Color.parseColor("#CBD890")), ColorDrawable(Color.parseColor("#DACC8F")),
    ColorDrawable(Color.parseColor("#D9A790")), ColorDrawable(Color.parseColor("#D18FD9")),
    ColorDrawable(Color.parseColor("#FF6772")), ColorDrawable(Color.parseColor("#DDFB5C"))
  )

  fun getRandomDrawbleColor(): ColorDrawable? {
    val idx = Random().nextInt(vibrantLightColorList.size)
    return vibrantLightColorList[idx]
  }
}