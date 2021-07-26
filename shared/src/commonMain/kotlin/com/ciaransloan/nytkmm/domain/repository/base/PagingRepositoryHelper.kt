package com.ciaransloan.nytkmm.domain.repository.base

import com.ciaransloan.nytkmm.domain.remote.base.ApiResult
import com.ciaransloan.nytkmm.domain.remote.model.NytResponseApiModel
import com.ciaransloan.nytkmm.domain.repository.base.model.Page
import kotlin.math.ceil

internal class PagingRepositoryHelper {

    suspend fun <T, R> callWithPaging(
        page: Int,
        modelProvider: suspend (offset: Int) -> (ApiResult<NytResponseApiModel<List<T>>>),
        modelMapper: (List<T>) -> List<R>,
        pageSize: Int,
    ): RepositoryResult<Page<R>> {
        if (page < 1) throw IllegalArgumentException("Cannot load page $page (min=1)")
        val offset = (page - 1) * pageSize
        val apiResult = modelProvider.invoke(offset)
        val totalObjectCount: Int
        val domainItems = when (apiResult) {
            is ApiResult.Success -> {
                totalObjectCount = apiResult.data.objectCount
                modelMapper(apiResult.data.results)
            }
            is ApiResult.Failure -> {
                return RepositoryResult.error(RepositoryError.fromException(apiResult.throwable))
            }
        }
        val pageCount = ceil(totalObjectCount.toDouble() / pageSize).toInt()
        if (pageCount == 0) {
            return RepositoryResult.success(Page.LastPage(emptyList()))
        }
        val pageModel = when (page) {
            pageCount -> Page.LastPage(domainItems)
            else -> Page.HasNextPage(
                items = domainItems,
                nextPage = { callWithPaging(page + 1, modelProvider, modelMapper, pageSize) }
            )
        }
        return RepositoryResult.success(pageModel)
    }
}