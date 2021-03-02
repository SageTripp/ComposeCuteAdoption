/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
