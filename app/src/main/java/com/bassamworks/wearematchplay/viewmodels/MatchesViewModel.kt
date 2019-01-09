package com.bassamworks.wearematchplay.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bassamworks.wearematchplay.constatnts.Constants.API.Matches.DEFAULT_PAGE_SIZE
import com.bassamworks.wearematchplay.models.ui.Match
import com.bassamworks.wearematchplay.paging.matches.MatchesDataSourceFactory
import com.bassamworks.wearematchplay.repositories.MatchesRepository

class MatchesViewModel(app: Application) : AndroidViewModel(app) {

    private val matchesRepo: MatchesRepository by lazy { MatchesRepository(app) }

    private val _matchesPagedList by lazy {
        LivePagedListBuilder(
            MatchesDataSourceFactory(matchesRepo),
            DEFAULT_PAGE_SIZE
        ).build()
    }

    val matchesPagedList: LiveData<PagedList<Match>> =
        Transformations.map(_matchesPagedList) { it }
}

