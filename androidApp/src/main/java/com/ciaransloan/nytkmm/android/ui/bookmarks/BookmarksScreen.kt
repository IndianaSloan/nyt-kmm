package com.ciaransloan.nytkmm.android.ui.bookmarks

import android.content.Intent
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
import com.ciaransloan.nytkmm.presentation.bookmark.model.BookmarkListState
import com.ciaransloan.nytkmm.presentation.bookmark.model.BookmarkUIModel

@Composable
fun BookmarksScreen() {
    val viewModel: BookmarksViewModel = hiltViewModel()
    val context = LocalContext.current

    val uiState = viewModel.uiState.collectAsState()
    val data = when (val model = uiState.value) {
        is BookmarkListState.Content -> model.items
        is BookmarkListState.Loading -> {
            ProgressMask()
            emptyList()
        }
        else -> emptyList()
    }

    BookmarkList(
        items = data,
        onClicked = { Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show() },
        onRemoveClicked = { viewModel.onRemoveClicked(it.id) },
        onShareClicked = { webUrl ->
            Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, webUrl)
                type = "text/plain"
                context.startActivity(this)
            }
        }
    )
}

@Composable
private fun BookmarkList(
    items: List<BookmarkUIModel>,
    onClicked: (BookmarkUIModel) -> Unit,
    onRemoveClicked: (BookmarkUIModel) -> Unit,
    onShareClicked: (String) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        itemsIndexed(items) { _, uiItem ->
            BookmarkListItem(
                bookmark = uiItem,
                onClicked = onClicked,
                onRemoveClicked = onRemoveClicked,
                onShareClicked = onShareClicked
            )
        }
    }
}

@Composable
private fun BookmarkListItem(
    bookmark: BookmarkUIModel,
    onClicked: (BookmarkUIModel) -> Unit,
    onRemoveClicked: (BookmarkUIModel) -> Unit,
    onShareClicked: (String) -> Unit
) {
    Card(
        modifier = Styles.ArticleCard.clickable { onClicked(bookmark) },
        elevation = Dimens.ElevationSmall
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Row(
                modifier = Modifier.padding(Dimens.PaddingDefault),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ArticleImage(bookmark)
                Spacer(modifier = Modifier.width(Dimens.PaddingHalf))
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = bookmark.title, style = Typography.h1)
                    Spacer(modifier = Modifier.height(Dimens.PaddingTwoThirds))
                    Divider(
                        color = ColorOnBackground,
                        modifier = Modifier.absolutePadding(
                            top = Dimens.PaddingFourth,
                            bottom = Dimens.PaddingFourth
                        )
                    )
                    Spacer(modifier = Modifier.height(Dimens.PaddingTwoThirds))
                    Text(text = bookmark.description, style = Typography.caption)
                }
            }
            Row(
                modifier = Modifier
                    .padding(Dimens.PaddingDefault)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = bookmark.postedDate,
                    style = Typography.subtitle2,
                    modifier = Modifier.absolutePadding(right = Dimens.PaddingHalf),
                    textAlign = TextAlign.Left
                )
                ImageDrawable(resId = R.drawable.ic_delete) { onRemoveClicked(bookmark) }
                ImageDrawable(resId = R.drawable.ic_share) { onShareClicked(bookmark.webUrl) }
            }
        }
    }
}


@Composable
private fun ArticleImage(article: BookmarkUIModel) {
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