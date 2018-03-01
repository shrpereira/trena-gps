package br.com.intelize.trenagps.ui.modes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.intelize.trenagps.R.layout.fragment_realtime

class RealtimeFragment : Fragment() {

    companion object {
        fun newInstance(): RealtimeFragment {
            return RealtimeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_realtime, container, false)
    }
}