package com.widyatama.nurseassistant.view.activity

import android.os.Bundle
import android.os.Handler
import androidx.viewpager.widget.ViewPager
import com.rd.PageIndicatorView
import com.widyatama.core.base.BaseActivity
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.adapter.viewPager.SlidingImageAdapter
import com.widyatama.nurseassistant.data.model.Image
import java.util.*

class EventActivity : BaseActivity() {
    companion object {
        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

    private var imageList: ArrayList<Image>? = null
    private val myImageList = intArrayOf(R.drawable.banner_event, R.drawable.banner_event, R.drawable.banner_event)

    override fun onInitializedView(savedInstanceState: Bundle?) {
        supportActionBar?.title = "Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageList = ArrayList()
        imageList = populateList()
        init()
    }

    override fun setLayout(): Int {
        return R.layout.activity_event
    }

    private fun populateList(): ArrayList<Image> {

        val list = ArrayList<Image>()

        for (i in 0..2) {
            val imageModel = Image()
            imageModel.setImagePath(myImageList[i])
            list.add(imageModel)
        }

        return list
    }

    private fun init() {

        mPager = findViewById(R.id.view_pager) as ViewPager
        mPager!!.adapter = SlidingImageAdapter(this, this.imageList!!)

        val indicator = findViewById(R.id.pagerIndicator) as PageIndicatorView
        indicator.setViewPager(mPager)
        val density = resources.displayMetrics.density

        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = imageList!!.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setViewPager(mPager)

    }


}
