package com.app.basemodule.presentation.base

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.basemodule.extensions.getAppUsableScreenSize
import com.app.basemodule.extensions.isTablet

abstract class BaseFragmentDialog : DialogFragment() {

    protected fun adjustDialogSize() {
        if (this.isTablet()) {
            val screenWidth = activity?.getAppUsableScreenSize()?.x ?: return
            val dialogNewWidth = (screenWidth - screenWidth / 2.6).toInt()
            dialog.window.setLayout(dialogNewWidth, ViewGroup.LayoutParams.WRAP_CONTENT)
        } else {
            dialog.window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    protected fun forceHeightWrapContent(v: View) {
        // Start with the provided view
        var current = v

        // Travel up the tree until fail, modifying the LayoutParams
        do {
            // Get the parent
            val parent = current.parent

            // Check if the parent exists
            if (parent != null) {
                // Get the view
                try {
                    current = parent as View
                } catch (e: ClassCastException) {
                    // This will happen when at the top view, it cannot be cast to a View
                    break
                }

                // Modify the layout
                current.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        } while (current.parent != null)

        // Request a layout to be re-done
        current.requestLayout()
    }
}