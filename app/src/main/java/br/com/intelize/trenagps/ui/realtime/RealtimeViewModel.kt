package br.com.intelize.trenagps.ui.realtime

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import br.com.intelize.trenagps.common.rx.SingleLiveEvent
import br.com.intelize.trenagps.common.util.DistanceUtil
import br.com.intelize.trenagps.extensions.isNotNull

class RealtimeViewModel : ViewModel() {

    private val initialLocation: MutableLiveData<Location> = MutableLiveData()
    private val finalLocation: MutableLiveData<Location> = MutableLiveData()
    val distance: SingleLiveEvent<Double> = SingleLiveEvent()
    val finalDistance: SingleLiveEvent<Double> = SingleLiveEvent()

    fun startMeasuring(location: Location) {
        distance.value = 0.0
        initialLocation.value = location
    }

    fun updateMeasuring(location: Location) {
        finalLocation.value = location

        val diffDistance = getDiffDistance()
        if (diffDistance > 0.0) {
            distance.value = distance.value?.plus(diffDistance)
            initialLocation.value = location
        }
    }

    fun finishMeasuring(location: Location) {
        updateMeasuring(location)

        finalDistance.value = distance.value
    }

    private fun getDiffDistance(): Double {
        if (initialLocation.value.isNotNull() && finalLocation.value.isNotNull()) {
            return DistanceUtil.meterDistanceBetweenPoints(
                    initialLocation.value!!.latitude,
                    initialLocation.value!!.longitude,
                    finalLocation.value!!.latitude,
                    finalLocation.value!!.longitude
            )
        }
        return 0.0
    }
}