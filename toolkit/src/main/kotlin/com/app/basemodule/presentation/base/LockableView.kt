package com.app.basemodule.presentation.base

interface LockableView {
    fun lockInterfaceOnRequest(requestId: Int? = -10)
    fun unlockInterfaceAfterRequest(requestId: Int? = -10)
}