package com.bassamworks.wearematchplay.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bassamworks.wearematchplay.R
import com.squareup.picasso.Picasso

class ImageSliderPagerAdapter(private val context: Context, private val images: Array<String>) : PagerAdapter() {

    private val inflater: LayoutInflater by lazy { context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view = inflater.inflate(R.layout.match_image_item, null)

        val ivMatch = view.findViewById<ImageView>(R.id.iv_match_image)

        if (images.isNotEmpty()) {
            container.visibility = View.VISIBLE
            Picasso.get().load(images[position]).into(ivMatch)
            (container as ViewPager).addView(view)
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeViewAt(0)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = images.size
}