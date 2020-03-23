package uz.kashtan.hamkortv.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import uz.kashtan.hamkortv.R

class HelpDialog(context: Context): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_help)
    }
}