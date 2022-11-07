package com.twaun95.observer.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.LifecycleOwner
import com.twaun95.observer.R
import com.twaun95.observer.databinding.FragmentDialogSimpleBinding

class SimpleDialogFragment : DialogFragment() {

    private var _binding : FragmentDialogSimpleBinding? = null
    private val binding get() = _binding!!

    fun interface ClickListener {
        fun onPositive()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.simple_dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_dialog_simple, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setEvent()
    }

    private fun setEvent() {
        binding.buttonNo.setOnClickListener { dismiss() }
        binding.buttonYes.setOnClickListener {
            setFragmentResult(requestKey = RESULT, result = bundleOf(RESULT_POSITIVE_ACTION to true))
            dismiss()
        }
    }

    companion object {
        private const val TAG = "SIMPLE_DIALOG_FRAGMENT"
        private const val RESULT_POSITIVE_ACTION = "result_positive_action"
        private const val RESULT = "result"

        fun testShow(fragmentManager: FragmentManager) { return SimpleDialogFragment().show(fragmentManager, TAG) }

        fun show(
            fragmentManager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: ClickListener
        ) {
            return SimpleDialogFragment().also {
                fragmentManager.setFragmentResultListener(RESULT, lifecycleOwner) { _, bundle ->
                    if (bundle.getBoolean(RESULT_POSITIVE_ACTION, false)) { listener.onPositive() }
                }
            }.show(fragmentManager, TAG)
        }
    }
}