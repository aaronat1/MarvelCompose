package com.aaronat1.marvelcompose.ui.screens.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.aaronat1.marvelcompose.R
import com.aaronat1.marvelcompose.data.entities.Character
import com.aaronat1.marvelcompose.data.entities.MarvelItem
import com.aaronat1.marvelcompose.ui.MarvelScreen

@Composable
fun <T: MarvelItem>MaverlItemBottomPreview(item: T?, onGoToDetail: (T) -> Unit) {
    if (item != null) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(item.thumbnail),
                contentDescription = item.title,
                modifier = Modifier
                    .width(96.dp)
                    .aspectRatio(1 / 1.5f)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                Text(text = item.description)
                Button(
                    onClick = { onGoToDetail(item) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = R.string.go_to_detail))
                }
            }
        }
        Text(text = item.title)
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }

}

@Preview
@Composable
fun MaverlItemBottomPrev() {
    MarvelScreen {
        MaverlItemBottomPreview(
            item = Character(
                id = 1,
                title = "Character 1",
                description =  "sdlfjasl dfasl dfjaslfd jasld fjalsfjalsfj lasjfalsjf alsjflasjf lasjfd",
                thumbnail = "",
                emptyList(),
                emptyList()
            ),
            onGoToDetail = {}
        )
    }

}