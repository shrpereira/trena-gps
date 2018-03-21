package br.com.intelize.trenagps.model

class MeasureType {

    companion object {
        const val MEASURE_TYPE_EXTRA = "measureTypeExtra"
    }

    enum class Type {
        STRAIGHT_LINE, REALTIME
    }

}