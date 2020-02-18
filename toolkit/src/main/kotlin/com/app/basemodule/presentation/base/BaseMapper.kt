package com.app.basemodule.presentation.base

import java.io.Serializable

interface BaseMapper<Source, Destination> : Serializable {
    fun toPresentation(from: Destination?): Source?
    fun toData(from: Source?): Destination?
}