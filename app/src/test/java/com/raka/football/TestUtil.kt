package com.raka.football

import androidx.lifecycle.SavedStateHandle
import io.mockk.every
import kotlinx.coroutines.flow.MutableStateFlow

inline fun <reified T> SavedStateHandle.mockReturn(key: String, value: T) {
    every { getStateFlow<T>(eq(key), any()) }.returns(MutableStateFlow(value))
    every { get<T>(eq(key)) }.returns(value)
}
