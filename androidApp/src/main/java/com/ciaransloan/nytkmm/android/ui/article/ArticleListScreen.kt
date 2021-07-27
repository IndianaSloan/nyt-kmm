package com.ciaransloan.nytkmm.android.ui.article

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.ciaransloan.nytkmm.android.R
import com.ciaransloan.nytkmm.android.styles.ColorBackground
import com.ciaransloan.nytkmm.android.styles.ColorPrimary
import com.ciaransloan.nytkmm.android.styles.Dimens
import com.ciaransloan.nytkmm.android.styles.Typography
import com.ciaransloan.nytkmm.android.ui.components.ProgressMask
import com.ciaransloan.nytkmm.domain.repository.model.Article
import com.ciaransloan.nytkmm.domain.repository.model.NewsSection
import com.ciaransloan.nytkmm.presentation.article.model.ArticleListState

@Composable
fun ArticleListScreen(section: NewsSection? = null) {
    val viewModel: ArticleListViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    val context = LocalContext.current

    uiState.value
    val data = when (val model = uiState.value) {
        is ArticleListState.Content -> model.items
        is ArticleListState.Loading -> {
            ProgressMask()
            emptyList()
        }
        else -> emptyList()
    }

    ArticleList(items = data, onClicked = {
        Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
    })

    DisposableEffect(key1 = viewModel) {
        viewModel.setSection(section)
        onDispose { }
    }
}

@Composable
fun ArticleList(items: List<Article>, onClicked: (item: Article) -> Unit) {
    LazyColumn {
        items(items) { ArticleListItem(article = it, onClicked = onClicked) }
    }
}

@Composable
private fun ArticleListItem(article: Article, onClicked: (item: Article) -> Unit) {
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
                    Divider(
                        color = ColorBackground,
                        modifier = Modifier.absolutePadding(
                            top = Dimens.PaddingFourth,
                            bottom = Dimens.PaddingFourth
                        )
                    )
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
                    text = "26 July",
                    style = Typography.caption,
                    modifier = Modifier.absolutePadding(right = Dimens.PaddingHalf),
                    textAlign = TextAlign.Left
                )
                DrawableImage(resId = R.drawable.ic_bookmark)
                DrawableImage(resId = R.drawable.ic_share)
            }
        }
    }
}

@Composable
fun DrawableImage(@DrawableRes resId: Int) {
    Image(
        painter = painterResource(resId),
        contentDescription = "",
        colorFilter = ColorFilter.tint(ColorPrimary),
        modifier = Modifier.absolutePadding(left = Dimens.PaddingHalf, right = Dimens.PaddingHalf)
    )
}

@Composable
fun ArticleImage(article: Article) {
    Image(
        painter = rememberImagePainter(
            data = article.thumbnailUrl,
            imageLoader = LocalImageLoader.current,
            builder = {
                placeholder(R.drawable.image_placholder)
                fallback(R.drawable.image_placholder)
                error(R.drawable.image_placholder)
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