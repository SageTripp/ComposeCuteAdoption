package com.example.androiddevchallenge.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetail(
    modifier: Modifier = Modifier,
    puppy: Puppy,
    isAdoption: Boolean = false,
    togglePuppyAdoption: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Surface {
        Column(modifier = modifier.verticalScroll(scrollState)) {
            Image(
                painter = painterResource(id = puppy.photo),
                contentDescription = "image of ${puppy.name}",
                Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Box {
                PuppyInfo(puppy)
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset((-12).dp, (-12).dp),
                    onClick = { togglePuppyAdoption() },
                ) {
                    val adoption = if (!isAdoption) {
                        Icons.Outlined.FavoriteBorder
                    } else {
                        Icons.Filled.Favorite
                    }
                    Icon(imageVector = adoption, contentDescription = "")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = puppy.desc, Modifier.padding(8.dp))
        }
    }
}

@Composable
private fun PuppyInfo(puppy: Puppy) {
    Column(Modifier.padding(12.dp)) {
        Text(text = puppy.name, style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(18.dp))
        Card(
            Modifier
                .fillMaxWidth(),
            elevation = 4.dp,
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(text = "Breed: ${puppy.breed}")
                Text(text = "Location: ${puppy.addressCity}")
                Text(text = "Age: ${puppy.age}")
                Text(text = "Size: ${puppy.size}")
            }
        }
    }
}

@Preview
@Composable
fun PuppyDetailPreview() {
    val puppy = Puppy(
        id = "30511817",
        name = "OLIVIA",
        age = "young",
        photo = R.drawable.img_olivia,
        desc = "OLIVIA 1 YEAR Lab/Pointer mix 40lb Olivia is a very shy girl that will need time to come out of her shell. Loud noises while walking do make her nervous so a confident and experienced owner is important. Since Olivia is nervous with new things, she can be reactive with other dogs if they are too overbearing. Since she will need additional training Olivia will do best in a home as an only dog until she is confident enough to understand she is safe. Olivia is decompressing in her foster home and with time, patience and love Olivia will make the perfect companion. She loves her humans and will follow them around the house wherever they go. If you are interested in adopting Olivia, Please fill out an adoption application located on our website. PLEASE NOTE: Meet and greets are conducted after an adoption application has been processed and approved. www.aplaceforace.org *NOTE - If this dog is listed on this site, he/she is still currently available; consideration is given to those who submit actual applications first. We receive an enormous amount of inquiries daily. Serious applicants, please apply! attn: due to the overwhelming response of applications, APFA is requesting a \$20 non-refundable application fee prior to completing our adoption application/process. However, once approved to adopt, your \$20 will be applied to the adoption fee.",
        addressCity = "Boston",
        size = "Large 61-100 lbs (28-45 kg)",
        sex = "f",
        breed = "Labrador Retriever"
    )
    var isAdoption by remember { mutableStateOf(false) }
    MyTheme {
        PuppyDetail(puppy = puppy, isAdoption = isAdoption) {
            isAdoption = !isAdoption
        }
    }
}
