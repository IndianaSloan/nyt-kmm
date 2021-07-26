package com.ciaransloan.nytkmm.domain.repository.base.model

import com.ciaransloan.nytkmm.domain.repository.base.RepositoryResult

internal sealed class Page<T>(val items: List<T>) {

    class LastPage<T>(items: List<T>) : Page<T>(items)

    class HasNextPage<T>(
        items: List<T>,
        val nextPage: suspend () -> RepositoryResult<Page<T>>
    ) : Page<T>(items)
}