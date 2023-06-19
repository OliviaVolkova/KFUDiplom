package com.example.kfu_app.presentation.common

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kfu_app.databinding.AlertDialogBinding

class AlertDialogFragment(
    private val action: () -> Unit,
    private val actionOnCancel: (() -> Unit)? = null,
) : DialogFragment() {

    private lateinit var binding: AlertDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AlertDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
                actionOnCancel?.invoke()
            }
            btnSubmit.setOnClickListener {
                action.invoke()
                dismiss()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }
}