package br.com.intelize.trenagps.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.intelize.trenagps.ui.straightLine.StraightLineFragment

class ModesPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

	override fun getItem(position: Int): Fragment {
//        return when (position) {
//            0 -> StraightLineFragment.newInstance()
//            else -> RealtimeFragment.newInstance()
//        }
		return StraightLineFragment.newInstance()
	}

	override fun getCount(): Int {
//        return 2
		return 1
	}
}