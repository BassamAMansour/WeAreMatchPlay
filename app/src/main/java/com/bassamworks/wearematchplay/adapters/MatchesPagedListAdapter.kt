package com.bassamworks.wearematchplay.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bassamworks.wearematchplay.databinding.ItemMatchFeedBinding
import com.bassamworks.wearematchplay.models.ui.Match
import com.bassamworks.wearematchplay.viewholders.MatchViewHolder

class MatchesPagedListAdapter : PagedListAdapter<Match, MatchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(ItemMatchFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Match>() {
            override fun areItemsTheSame(oldItem: Match, newItem: Match) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Match, newItem: Match) = oldItem == newItem
        }
    }
}