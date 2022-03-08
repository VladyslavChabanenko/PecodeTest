package com.chb.pecodetest

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.chb.pecodetest.Constants.INITIAL_PAGE_COUNT
import com.chb.pecodetest.Constants.SHARED_PREFS_PAGE_COUNT_KEY
import com.chb.pecodetest.databinding.FragmentMainBinding
import com.chb.pecodetest.di.DaggerAppComponent
import com.chb.pecodetest.di.PagerAdapterModule
import javax.inject.Inject

class MainFragment(private val pageNumber: Int) : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewPager: ViewPager2

    @Inject
    lateinit var sharedPrefs: SharedPreferences

    @Inject
    lateinit var pagerAdapter: PecodeTestPagerAdapter

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.factory().create(requireContext()).getActivityComponent(
            PagerAdapterModule(requireActivity())
        ).inject(this)
        viewPager = requireActivity().findViewById(R.id.view_pager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setListeners() {
        with(binding) {
            btnNewNotification.setOnClickListener {
                notificationHelper.showNotification(
                    pageNumber,
                    getString(R.string.notification_message) + pageNumber.toString()
                )
            }
            fabAdd.setOnClickListener {
                savePageCount(getPageCount() + 1)
                createFragment()
            }
            if (pageNumber != 1) {
                fabRemove.setOnClickListener {
                    savePageCount(getPageCount() - 1)
                    deleteFragment()
                }
            }
        }
    }

    private fun setViews() {
        with(binding) {
            tvCount.text = pageNumber.toString()
            if (pageNumber > 1) {
                fabRemove.isVisible = true
            }
        }
    }

    private fun createFragment() {
        pagerAdapter.addFragment()
        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(getPageCount(), false)
    }

    private fun deleteFragment() {
        pagerAdapter.removeFragment()
        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(pageNumber - 1, false)
        notificationHelper.deleteNotification(getPageCount() + 1)
    }

    private fun savePageCount(number: Int) {
        sharedPrefs.edit().putInt(SHARED_PREFS_PAGE_COUNT_KEY, number).apply()
    }

    private fun getPageCount(): Int {
        return sharedPrefs.getInt(SHARED_PREFS_PAGE_COUNT_KEY, INITIAL_PAGE_COUNT)
    }
}