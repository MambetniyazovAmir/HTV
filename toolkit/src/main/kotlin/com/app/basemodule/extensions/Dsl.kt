package com.app.basemodule.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

fun Fragment.childFragmentTransaction(init: FragmentTransaction.() -> Unit) {
    if (!activity!!.isFinishing) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.init()
        transaction.commitAllowingStateLoss()
    }
}

fun AppCompatActivity.supportFragmentTransaction(init: FragmentTransaction.() -> Unit) {
    if (!isFinishing) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.init()
        transaction.commit()
    }
}

/**
supportFragmentTransaction {
if (backStack) {
addToBackStack(fragment.TAG)
}
if (withAnimation) {
setCustomAnimations(com.app.basemodule.R.anim.right_in, com.app.basemodule.R.anim.left_out,
com.app.basemodule.R.anim.left_in, com.app.basemodule.R.anim.right_out)
}
replace(fragmentContainer.id, fragment)
}
 */

fun AppCompatActivity.supportStateLosFragmentTransaction(init: FragmentTransaction.() -> Unit) {
    if (!isFinishing) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.init()
        transaction.commitNowAllowingStateLoss()
    }
}

fun AppCompatActivity.executeIfNotVisible(tag: String, init: FragmentTransaction.() -> Unit) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    if (fragment == null || !fragment.isVisible) {
        if (!isFinishing) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.init()
            transaction.commitNowAllowingStateLoss()
        }
    }
}

/**
 * Hos no effect if fragment is already on screen!
 */
fun AppCompatActivity.showOrReplaceLast(tag: String, replaceFunc: FragmentTransaction.() -> Unit) {
    val manager = supportFragmentManager
    val fragment = manager.findFragmentByTag(tag)
    if (fragment?.isHidden == true) {
        manager.beginTransaction().show(fragment).commit()
    }

    if (fragment == null || !fragment.isVisible) {
        if (!isFinishing) {
            val transaction = manager.beginTransaction()
            transaction.replaceFunc()
            transaction.commit()
        }
    }
}

fun Fragment.showOrReplaceLast(tag: String, replaceFunc: FragmentTransaction.() -> Unit) {
    val manager = childFragmentManager
    val fragment = manager.findFragmentByTag(tag)
    if (fragment?.isHidden == true) {
        manager.beginTransaction().show(fragment).commit()
    }

    if (fragment == null || !fragment.isVisible) {
        if (activity?.isFinishing?.not() == true) {
            val transaction = manager.beginTransaction()
            transaction.replaceFunc()
            transaction.commit()
        }
    }
}

inline fun <reified T : Fragment> AppCompatActivity.fragmentById(id: Int): T? =
        supportFragmentManager?.findFragmentById(id) as? T

inline fun <reified T : Fragment> Fragment.fragmentById(id: Int): T? =
        childFragmentManager?.findFragmentById(id) as? T

inline fun <reified T : Fragment> AppCompatActivity.fragmentByTag(tag: String): T? =
        supportFragmentManager?.findFragmentByTag(tag) as? T

inline fun <reified T : Fragment> Fragment.fragmentByTag(tag: String): T? =
        childFragmentManager?.findFragmentByTag(tag) as? T
