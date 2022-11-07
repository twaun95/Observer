package com.twaun95.observer.observer

object SubjectImpl : Subject  {
    private var observerList = mutableListOf<Observer>()
    private var tag = ""
    private var data = 0

    override fun registerObserver(observer: Observer) {
        observerList.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observerList.remove(observer)
    }

    override fun notifyObservers() {
        observerList.forEach { it.update(tag, data) }
    }

    fun getDataToString() = data.toString()

    fun setData(newTag: String, newData: Int) {
        this.tag = newTag
        this.data += newData
        notifyObservers()
    }
}