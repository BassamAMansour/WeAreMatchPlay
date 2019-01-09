package com.bassamworks.wearematchplay.paging.matches

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bassamworks.wearematchplay.models.ui.Match
import com.bassamworks.wearematchplay.repositories.MatchesRepository

class MatchesDataSourceFactory(private val repository: MatchesRepository) : DataSource.Factory<Int, Match>() {

    val matchesDataSourceLiveData: MutableLiveData<MatchesDataSource> = MutableLiveData()


    override fun create(): DataSource<Int, Match> {
        val matchesDataSource = MatchesDataSource(repository)
        matchesDataSourceLiveData.postValue(matchesDataSource)
        return matchesDataSource
    }

}