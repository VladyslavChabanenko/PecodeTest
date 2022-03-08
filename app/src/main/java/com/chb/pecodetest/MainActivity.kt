package com.chb.pecodetest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.chb.pecodetest.Constants.INITIAL_PAGE_COUNT
import com.chb.pecodetest.Constants.NOTIFICATION_CLICKED_KEY
import com.chb.pecodetest.Constants.SHARED_PREFS_PAGE_COUNT_KEY
import com.chb.pecodetest.databinding.ActivityMainBinding
import com.chb.pecodetest.di.DaggerAppComponent
import com.chb.pecodetest.di.PagerAdapterModule
import javax.inject.Inject

class MainActivity : FragmentActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private var pageCount = INITIAL_PAGE_COUNT

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    @Inject
    lateinit var pagerAdapter: PecodeTestPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.factory().create(applicationContext).getActivityComponent(
            PagerAdapterModule(this)
        ).inject(this)

        pageCount = sharedPrefs.getInt(SHARED_PREFS_PAGE_COUNT_KEY, INITIAL_PAGE_COUNT)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewPager = binding.viewPager
        viewPager.adapter = pagerAdapter

        setContentView(binding.root)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            viewPager.setCurrentItem(
                intent.getIntExtra(NOTIFICATION_CLICKED_KEY, INITIAL_PAGE_COUNT) - 1,
                false
            )
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
}