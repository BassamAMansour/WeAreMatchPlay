package com.bassamworks.wearematchplay.viewholders

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bassamworks.wearematchplay.adapters.ImageSliderPagerAdapter
import com.bassamworks.wearematchplay.R
import com.bassamworks.wearematchplay.databinding.ItemMatchFeedBinding
import com.bassamworks.wearematchplay.models.ui.Match

class MatchViewHolder(private val binding: ItemMatchFeedBinding) : RecyclerView.ViewHolder(binding.root) {

    private val context: Context? by lazy { itemView.context }

    fun bindTo(model: Match) {

        binding.apply {
            match = model
            executePendingBindings()
        }

        updateRemainingLayouts(model)
    }

    private fun updateRemainingLayouts(match: Match) {
        updateInteractionLayout(match)
        updatePlayersLayout(match)
        updateImageSliderLayout(match)
    }

    private fun updateImageSliderLayout(match: Match) {
        binding.vpMatchImages.apply {
            adapter = context?.let {
                ImageSliderPagerAdapter(
                    it,
                    match.imagesUrls
                )
            }
        }
    }

    private fun updatePlayersLayout(match: Match) {
        binding.includerPlayersLayout.apply {

            tvPlayer1Points.background =
                    if (match.playersScores[Match.getPlayerPosition(1)].isNotEmpty()) context?.getDrawable(R.color.colorAccent)
                    else context?.getDrawable(android.R.color.white)

            tvPlayer2Points.background =
                    if (match.playersScores[Match.getPlayerPosition(2)].isNotEmpty()) context?.getDrawable(R.color.colorAccent)
                    else context?.getDrawable(android.R.color.white)
        }
    }

    private fun updateInteractionLayout(match: Match) {
        binding.includerInteractionLayout.apply {

            btnLikes.setCompoundDrawablesRelative(
                context?.getDrawable(getLikesDrawableId(match.isLiked)),
                null,
                null,
                null
            )

            btnVerificationStatus.text = getVerificationText(match.isVerified)
            btnVerificationStatus.setCompoundDrawablesRelative(
                context?.getDrawable(getVerificationDrawable(match.isVerified)),
                null,
                null,
                null
            )
        }
    }

    private fun getVerificationDrawable(verified: Boolean): Int =
        if (verified) R.drawable.verified_bg else R.drawable.not_verified_bg


    private fun getVerificationText(verified: Boolean): CharSequence? =
        if (verified) context?.getString(R.string.verified) else context?.getString(R.string.not_verified)

    private fun getLikesDrawableId(liked: Boolean): Int =
        if (liked) R.drawable.like_btn_liked_bg else R.drawable.like_btn_not_liked_bg
}