package com.mahdizaredev.onlineshop.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahdizaredev.onlineshop.ui.components.LoadingInColumn
import com.mahdizaredev.onlineshop.ui.components.LoadingInRow
import com.mahdizaredev.onlineshop.ui.components.products.ProductListItemView
import com.mahdizaredev.onlineshop.viewmodels.products.ProductByCategoryViewModel
import kotlin.collections.get
import kotlin.times

@Composable
fun ProductsScreen(
    categoryId: Long,
    title: String,
    navController: NavHostController,
    viewModel: ProductByCategoryViewModel = hiltViewModel()
) {
    var dataList by remember { mutableStateOf(viewModel.dataList) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }
    val animatedVisibleState = remember { MutableTransitionState(false) }
        .apply { targetState = true }

    LazyColumn(Modifier.padding(20.dp, 0.dp)) {
        item {
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500, 500),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 500),
                ),
                exit = fadeOut()
            ) {
                Text(text = title, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(dataList.value.size) { index ->
            viewModel.onScrollPositionChange(index)
            if ((index + 1) >= (viewModel.pageIndex.value * viewModel.pageSize) &&
                !viewModel.isLoading.value
            ) {
                viewModel.nextPage()
            }
            AnimatedVisibility(
                visibleState = animatedVisibleState,
                enter = slideInVertically(
                    animationSpec = tween(500, 1000),
                    initialOffsetY = { -40 }
                ) + fadeIn(
                    animationSpec = tween(500, 1000),
                ),
                exit = fadeOut()
            ) {
                ProductListItemView(dataList.value[index], navController)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (isLoading.value) {
            item {
                LoadingInColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }


}