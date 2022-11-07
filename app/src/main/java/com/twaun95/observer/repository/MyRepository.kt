package com.twaun95.observer.repository

import android.util.Log
import kotlin.properties.Delegates

object MyRepository {
    var data: String by Delegates.observable(
        initialValue = "",
        onChange = { property, oldValue, newValue ->
            Log.d("Taewaun", "property: $property, oldValue: $oldValue, newValue: $newValue")
        }
    )
}
