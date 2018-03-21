package br.com.intelize.trenagps.ui.realtime

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.intelize.trenagps.R.layout.fragment_realtime
import kotlinx.android.synthetic.main.fragment_realtime.*

class RealtimeFragment : Fragment() {

    companion object {

        const val saveMeasureResultCode = 1

        fun newInstance(): RealtimeFragment {
            return RealtimeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragment_realtime, container, false)
    }

    override fun onStart() {
        super.onStart()

        configureAction()
    }

    private fun configureAction() {
        innerCircle.setOnClickListener {
            //            (activity as MainActivity).redirectToSaveScreen()
        }
    }
}