package br.com.intelize.trenagps.ui.modes

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ModesPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> StraightLineFragment.newInstance()
            else -> RealtimeFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}