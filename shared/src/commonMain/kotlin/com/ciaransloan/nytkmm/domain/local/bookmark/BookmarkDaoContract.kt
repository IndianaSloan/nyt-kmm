package com.ciaransloan.nytkmm.domain.local.bookmark

import com.ciaransloan.nytkmm.domain.repository.model.Article

internal interface BookmarkDaoContract {
    fun insert(article: Article)
    fun insert(items: List<Article>)
    fun getBookmarkIds(): List<String>
    fun getArticlesBookmarked(): List<Article>
    fun remove(articleId: String)
}