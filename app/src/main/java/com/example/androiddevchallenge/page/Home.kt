package com.example.androiddevchallenge.page

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.MainViewModel

@Composable
fun Home() {
  val mainVm: MainViewModel = viewModel()
  val puppies = mainVm.puppies

  PuppyList(
    puppies,
    Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    isAdoption = mainVm::isAdoption,
    togglePuppyAdoption = mainVm::toggleAdoption,
    onPuppyClick = mainVm::showPuppyDetail
  )

  val currentPuppy = mainVm.currentPuppy
  if (null != currentPuppy) {
    val isAdoption = mainVm.isAdoption(currentPuppy)
    PuppyDetail(puppy = currentPuppy, modifier = Modifier.fillMaxSize(), isAdoption = isAdoption) {
      mainVm.toggleAdoption(currentPuppy)
    }
  }

}
