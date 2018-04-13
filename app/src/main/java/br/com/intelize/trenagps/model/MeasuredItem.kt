package br.com.intelize.trenagps.model

import java.util.*

data class MeasuredItem(
        val type: MeasureType.Type,
        val name: String,
        val distance: String,
        val registerDt: Date
)