package com.twaun95.observer.observer

class Subscriber(
    private val name: String,
    private val subject: Subject
) : Observer {

    init {
        subject.registerObserver(this)
    }

    private var liveData = 0
    var updateListener : ((data: String)->Unit)? = null

    override fun update(tag: String, data: Int) {
        liveData = data
        updateListener?.invoke(liveData.toString())
    }

    fun reset() {
        updateListener = null
        subject.removeObserver(this)
    }
}