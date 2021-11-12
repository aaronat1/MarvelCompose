package com.aaronat1.marvelcompose.ui.commons

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.aaronat1.marvelcompose.R

@Composable
fun LoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize() ){
        CircularProgressIndicator(
            color = colorResource(id = R.color.purple_200),
            strokeWidth = 15.dp,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .padding(16.dp))
    }
}