package com.example.androiddevchallenge.data

import androidx.annotation.DrawableRes

data class Puppy(
  val name: String = "",
  val addressCity: String = "",
  val age: String = "",
  val id: String = "",
  val breed: String = "",
  @DrawableRes val photo: Int = 0,
  val sex: String = "",
  val size: String = "",
  val desc: String = "",
)
