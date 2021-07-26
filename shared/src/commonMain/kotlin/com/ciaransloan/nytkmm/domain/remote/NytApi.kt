package com.ciaransloan.nytkmm.domain.remote

import com.ciaransloan.nytkmm.domain.remote.base.BaseApi
import com.ciaransloan.nytkmm.domain.remote.model.ArticleApiModel
import com.ciaransloan.nytkmm.domain.remote.model.NytResponseApiModel
import com.ciaransloan.nytkmm.domain.remote.model.SectionApiModel
import io.ktor.client.*
import io.ktor.client.request.*

// Note: That KTOR makes API requests on background thread under the hood, even
// if the request is made on MainThread
internal class NytApi(private val httpClient: HttpClient) : BaseApi() {

    suspend fun getArticles(section: String, offset: Int = 0) = apiCall {
        httpClient.get<NytResponseApiModel<List<ArticleApiModel>>>("$BASE_URL/nyt/$section.json") {
            parameter(PARAM_OFFSET, offset)
            parameter(PARAM_LIMIT, PAGE_SIZE)
        }
    }

    suspend fun getSections() = apiCall {
        httpClient.get<NytResponseApiModel<List<SectionApiModel>>>("$BASE_URL/section-list.json") {
            parameter(PARAM_LIMIT, PAGE_SIZE)
        }
    }
}