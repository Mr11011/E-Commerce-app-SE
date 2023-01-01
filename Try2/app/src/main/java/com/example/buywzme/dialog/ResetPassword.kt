package com.example.buywzme.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.buyweme.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit,


    ) {
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle )
    val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()


    val edEmail = view.findViewById<EditText>(R.id.edReset)
    val sendBtn = view.findViewById<Button>(R.id.send_btn)
    val cancelBtn = view.findViewById<Button>(R.id.cancel_btn)

    sendBtn.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    cancelBtn.setOnClickListener {
        dialog.dismiss()
    }


}