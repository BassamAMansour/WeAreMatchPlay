package com.bassamworks.wearematchplay.paging.matches

import androidx.paging.PageKeyedDataSource
import com.bassamworks.wearematchplay.models.ui.Match
import com.bassamworks.wearematchplay.repositories.MatchesRepository

class MatchesDataSource(private val repository: MatchesRepository) : PageKeyedDataSource<Int, Match>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Match>) {
        repository.getInitialPage({
            callback.onResult(
                it,
                null,
                repository.currentPage + 1
            )

        }, {})
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {
        repository.getPage(
            {
                callback.onResult(
                    it,
                    if (repository.currentPage >= repository.maxPages) null else repository.currentPage + 1
                )
            }, {}, params.key
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Match>) {
        repository.getPage(
            {
                callback.onResult(
                    it,
                    if (repository.currentPage <= MatchesRepository.INITIAL_PAGE_NUMBER) null else repository.currentPage - 1
                )
            }, {}, params.key
        )
    }

}