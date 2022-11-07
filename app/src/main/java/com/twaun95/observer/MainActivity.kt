package com.twaun95.observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.twaun95.observer.databinding.ActivityMainBinding
import com.twaun95.observer.fragments.FirstFragment
import com.twaun95.observer.observer.SubjectImpl
import com.twaun95.observer.observer.Subscriber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val subscriber = Subscriber("Activity", SubjectImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setObserver()
        setEvent()
    }

    private fun setObserver() {
        subscriber.updateListener = { binding.textData.text = it }
    }

    private fun setEvent() {
        binding.textData.text = SubjectImpl.getDataToString()
        binding.buttonNext.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.frameLayout_main, FirstFragment.newInstance()).addToBackStack(null).commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriber.reset()
    }
}