package com.chb.pecodetest

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PecodeTestPagerAdapter(fragmentActivity: FragmentActivity, private var fragmentsCount: Int): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentsCount

    override fun createFragment(position: Int) = MainFragment(position + 1)

    fun addFragment() {
        fragmentsCount++
    }

    fun removeFragment() {
        fragmentsCount--
    }
}