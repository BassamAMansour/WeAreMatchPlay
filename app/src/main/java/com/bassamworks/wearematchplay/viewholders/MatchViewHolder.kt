package com.bassamworks.wearematchplay.viewholders

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bassamworks.wearematchplay.R
import com.bassamworks.wearematchplay.adapters.ImageSliderPagerAdapter
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
        updateDetailsLayout(match)
    }

    private fun updateDetailsLayout(match: Match) {
        binding.includerDetailsLayout.apply {
            tvMatchDescription.visibility = if (match.matchDescription.isEmpty()) View.GONE else View.VISIBLE
        }
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

        binding.tabLayoutMatchImages.apply {
            setupWithViewPager(binding.vpMatchImages)
            visibility = if (match.imagesUrls.size <= 1) View.GONE else View.VISIBLE
        }

    }

    private fun updatePlayersLayout(match: Match) {
        binding.includerPlayersLayout.apply {

            val playerOnePoints = match.playersScores[Match.getPlayerPosition(1)]
            val playerTwoPoints = match.playersScores[Match.getPlayerPosition(2)]

            if (playerOnePoints.isEmpty() && playerTwoPoints.isEmpty()) {
                tvTie.visibility = View.VISIBLE
            } else {
                tvTie.visibility = View.GONE

                tvPlayer1Points.background =
                        if (playerOnePoints.isNotEmpty()) context?.getDrawable(R.color.colorAccent)
                        else context?.getDrawable(android.R.color.white)

                tvPlayer2Points.background =
                        if (playerTwoPoints.isNotEmpty()) context?.getDrawable(R.color.colorAccent)
                        else context?.getDrawable(android.R.color.white)
            }

        }
    }

    private fun updateInteractionLayout(match: Match) {
        binding.includerInteractionLayout.apply {

            val likesDrawable = context?.getDrawable(getLikesDrawableId(match.isLiked))
            btnLikes.setCompoundDrawablesWithIntrinsicBounds(
                likesDrawable,
                null,
                null,
                null
            )

            tvVerificationStatus.text = getVerificationText(match.isVerified)
            tvVerificationStatus.setTextColor(getVerificationTextColor(match.isVerified))

            val verificationDrawable = context?.getDrawable(getVerificationDrawable(match.isVerified))
            tvVerificationStatus.setCompoundDrawablesWithIntrinsicBounds(
                verificationDrawable,
                null,
                null,
                null
            )
        }
    }

    private fun getVerificationTextColor(verified: Boolean): Int {
        var color = 0
        context?.let {
            color = if (verified) ContextCompat.getColor(it, android.R.color.holo_green_light)
            else ContextCompat.getColor(it, android.R.color.holo_red_light)
        }
        return color
    }

    private fun getVerificationDrawable(verified: Boolean): Int =
        if (verified) R.drawable.verified_bg else R.drawable.not_verified_bg

    private fun getVerificationText(verified: Boolean): CharSequence? =
        if (verified) context?.getString(R.string.verified) else context?.getString(R.string.not_verified)

    private fun getLikesDrawableId(liked: Boolean): Int =
        if (liked) R.drawable.like_btn_liked_bg else R.drawable.like_btn_not_liked_bg
}