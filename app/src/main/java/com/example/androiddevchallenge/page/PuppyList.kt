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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy

@Composable
fun PuppyList(
    puppies: List<Puppy>,
    modifier: Modifier = Modifier,
    isAdoption: (puppy: Puppy) -> Boolean = { false },
    togglePuppyAdoption: (puppy: Puppy) -> Unit = {},
    onPuppyClick: (puppy: Puppy) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Puppy Adoption")
                }
            )
        },
    ) {
        LazyColumn(modifier) {
            items(puppies) { puppy ->
                PuppyItem(
                    puppy = puppy,
                    Modifier.padding(vertical = 6.dp),
                    isAdoption = isAdoption(puppy),
                    togglePuppyAdoption = { togglePuppyAdoption(puppy) }
                ) {
                    onPuppyClick(puppy)
                }
            }
        }
    }
}

@Composable
private fun PuppyItem(puppy: Puppy, modifier: Modifier = Modifier, isAdoption: Boolean = false, togglePuppyAdoption: () -> Unit = {}, onPuppyClick: () -> Unit = {}) {
    Row(
        modifier = modifier.clickable { onPuppyClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val imageModifier = Modifier
            .size(88.dp)
            .clip(CircleShape)
            .zIndex(100f)

        Image(
            painter = painterResource(id = puppy.photo),
            contentDescription = "picture of ${puppy.name}",
            modifier = imageModifier
        )

        PuppyInfo(puppy, isAdoption, togglePuppyAdoption)
    }
}

@Composable
private fun PuppyInfo(puppy: Puppy, isAdoption: Boolean, togglePuppyAdoption: () -> Unit = {}) {
    Surface(
        Modifier
            .offset(x = (-32).dp)
            .fillMaxWidth()
            .requiredHeight(64.dp)
            .clip(RoundedCornerShape(topEndPercent = 50, bottomEndPercent = 50)),
        color = MaterialTheme.colors.surface
    ) {
        Row(Modifier.padding(start = 52.dp, end = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
                Text(text = puppy.name, style = MaterialTheme.typography.subtitle1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = "address of ${puppy.name}")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = puppy.addressCity, style = MaterialTheme.typography.body2)
                }
            }
            val adoption = if (!isAdoption) {
                Icons.Outlined.FavoriteBorder
            } else {
                Icons.Filled.Favorite
            }
            Icon(
                imageVector = adoption,
                contentDescription = "",
                Modifier
                    .size(32.dp)
                    .clickable { togglePuppyAdoption() },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PuppyItemPreview() {
    val puppy = Puppy(id = "30511817", name = "OLIVIA", age = "young", photo = R.drawable.img_olivia, desc = "", addressCity = "Boston", size = "Large 61-100 lbs (28-45 kg)", sex = "f", breed = "Labrador Retriever")
    PuppyItem(puppy, Modifier.size(240.dp, 84.dp))
}

@Preview
@Composable
fun PuppyListPreview() {
    val puppies = listOf(
        Puppy(id = "30511817", name = "OLIVIA", age = "young", photo = R.drawable.img_olivia, desc = "", addressCity = "Boston", size = "Large 61-100 lbs (28-45 kg)", sex = "f", breed = "Labrador Retriever"),
        Puppy(id = "30626113", name = "TSUNAMI", age = "young", photo = R.drawable.img_tsunami, desc = "", addressCity = "Boston", size = "Med. 26-60 lbs (12-27 kg)", sex = "m", breed = "American Pit Bull Terrier"),
        Puppy(id = "30460740", name = "ARTHUR", age = "adult", photo = R.drawable.img_arthur, desc = "", addressCity = "Boston", size = "Large 61-100 lbs (28-45 kg)", sex = "m", breed = "German Shepherd Dog"),
    )
    PuppyList(puppies = puppies)
}
