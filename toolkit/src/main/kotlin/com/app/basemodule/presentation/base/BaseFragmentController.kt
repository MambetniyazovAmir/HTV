package com.app.basemodule.presentation.base

interface BaseFragmentController {
    fun showFragment(frag: BaseNetworkFragment)
    fun popBackStack()
}