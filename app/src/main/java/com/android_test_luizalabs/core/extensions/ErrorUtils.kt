package com.android_test_luizalabs.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

internal fun <T> Flow<T>.parseHttpError(): Flow<T> {
    return catch { throwable ->
        throw throwable.toRequestThrowable()
    }
}