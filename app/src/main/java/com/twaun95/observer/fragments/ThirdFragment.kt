package com.twaun95.observer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.twaun95.observer.R
import com.twaun95.observer.databinding.FragmentThirdBinding
import com.twaun95.observer.observer.SubjectImpl
import com.twaun95.observer.observer.Subscriber

class ThirdFragment : Fragment() {
    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!

    private lateinit var callback: OnBackPressedCallback
    private val subscriber = Subscriber("Fragment3", SubjectImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().remove(this@ThirdFragment).commit()
                childFragmentManager.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        setEvent()
    }

    private fun setObserver() {
        subscriber.updateListener = { binding.textData.text = it }
    }

    private fun setEvent() {
        binding.textData.text = SubjectImpl.getDataToString()
        binding.buttonThird.setOnClickListener {
            SubjectImpl.setData("Add", 5)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscriber.reset()
        _binding = null
    }

    companion object {
        fun newInstance() : ThirdFragment = ThirdFragment()
    }
}