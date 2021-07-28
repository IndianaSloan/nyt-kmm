package com.ciaransloan.nytkmm.android.ui.article

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.styles.*
import com.ciaransloan.nytkmm.android.ui.components.ImageDrawable
import com.ciaransloan.nytkmm.android.ui.components.ProgressMask
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState
import com.ciaransloan.nytkmm.presentation.article.model.ArticleUIModel
import com.ciaransloan.nytkmm.presentation.section.model.SectionUIModel

@Composable
fun ArticleListScreen(section: SectionUIModel? = null) {
    val viewModel: ArticleListViewModel = hiltViewModel()
    val context = LocalContext.current

    DisposableEffect(key1 = viewModel) {
        viewModel.setSection(section)
        onDispose { }
    }

    val uiState = viewModel.uiState.collectAsState()
    val data = when (val model = uiState.value) {
        is ArticleListState.Content -> model.items
        is ArticleListState.Loading -> {
            ProgressMask()
            emptyList()
        }
        else -> emptyList()
    }

    ArticleList(
        items = data,
        onClicked = { Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show() },
        onBookmarkClicked = { },
        onShareClicked = { webUrl ->
            Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, webUrl)
                type = "text/plain"
                context.startActivity(this)
            }
        },
        onScrolledToEnd = { viewModel.onPageScrolledToEnd() }
    )
}

@Composable
private fun ArticleList(
    items: List<ArticleUIModel>,
    onClicked: (ArticleUIModel) -> Unit,
    onBookmarkClicked: (ArticleUIModel) -> Unit,
    onShareClicked: (String) -> Unit,
    onScrolledToEnd: () -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        itemsIndexed(items) { index, uiItem ->
            if (index == items.lastIndex) onScrolledToEnd()
            ArticleListItem(
                article = uiItem,
                onClicked = onClicked,
                onBookmarkClicked = onBookmarkClicked,
                onShareClicked = onShareClicked
            )
        }
    }
}

@Composable
private fun ArticleListItem(
    article: ArticleUIModel,
    onClicked: (ArticleUIModel) -> Unit,
    onBookmarkClicked: (ArticleUIModel) -> Unit,
    onShareClicked: (String) -> Unit
) {
    Card(
        modifier = Styles.ArticleCard.clickable { onClicked(article) },
        elevation = Dimens.ElevationSmall
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Row(
                modifier = Modifier.padding(Dimens.PaddingDefault),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArticleImage(article)
                Spacer(modifier = Modifier.width(Dimens.PaddingHalf))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = article.title, style = Typography.h1)
                    Spacer(modifier = Modifier.height(Dimens.PaddingTwoThirds))
                    Divider(
                        color = ColorOnBackground,
                        modifier = Modifier.absolutePadding(
                            top = Dimens.PaddingFourth,
                            bottom = Dimens.PaddingFourth
                        )
                    )
                    Spacer(modifier = Modifier.height(Dimens.PaddingTwoThirds))
                    Text(text = article.description, style = Typography.caption)
                }
            }
            Row(
                modifier = Modifier
                    .padding(Dimens.PaddingDefault)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = article.postedDate,
                    style = Typography.subtitle2,
                    modifier = Modifier.absolutePadding(right = Dimens.PaddingHalf),
                    textAlign = TextAlign.Left
                )
                ImageDrawable(
                    resId = R.drawable.ic_bookmark,
                    tint = if (article.isFavorite) ColorPrimary else ColorSecondary
                ) { onBookmarkClicked(article) }
                ImageDrawable(resId = R.drawable.ic_share) { onShareClicked(article.webUrl) }
            }
        }
    }
}


@Composable
private fun ArticleImage(article: ArticleUIModel) {
    Image(
        painter = rememberImagePainter(
            data = article.thumbnailUrl,
            imageLoader = LocalImageLoader.current,
            builder = {
                placeholder(R.drawable.image_placholder)
                fallback(R.drawable.img_placeholder)
                error(R.drawable.img_placeholder)
            }
        ),
        contentDescription = null,
        modifier = Styles.ArticleImage,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

private object Styles {
    val ArticleCard: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(Dimens.PaddingHalf)

    val ArticleImage: Modifier = Modifier
        .size(Dimens.ArticleThumb)
        .clip(shape = RoundedCornerShape(Dimens.RadiusDefault))
}