package com.vladus177.currencycheck.common

import com.vladus177.currencycheck.common.extensions.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class ResultUseCase<Q, T>(
    override val backgroundContext: CoroutineContext,
    override val foregroundContext: CoroutineContext
) : BaseUseCase<Q, LiveResult<T>>(
    backgroundContext, foregroundContext
) {
    protected abstract suspend fun executeOnBackground(params: Q): T?

    override fun execute(liveData: LiveResult<T>, params: Q) {
        CoroutineScope(foregroundContext + newJob()).launch {
            liveData.postLoading()

            runCatching {
                withContext(backgroundContext) { executeOnBackground(params)!! }
            }.onSuccess { response ->
                liveData.postSuccess(response)
            }.onFailure { throwable ->
                when (throwable) {
                    is CancellationException -> liveData.postCancel()
                    is NullPointerException -> liveData.postEmpty()
                    else -> liveData.postThrowable(throwable)
                }
            }
        }
    }

    override fun executeRepeating(liveData: LiveResult<T>, params: Q, repeatTime: Long) {
        CoroutineScope(foregroundContext + newJob()).launch {
            var firstCall = true

            while (isActive) {
                delay(if (firstCall) 100L else 1000L)
                firstCall = false
                liveData.postLoading()

                runCatching {
                    withContext(backgroundContext) { executeOnBackground(params)!! }
                }.onSuccess { response ->
                    liveData.postSuccess(response)
                }.onFailure { throwable ->
                    when (throwable) {
                        is CancellationException -> liveData.postCancel()
                        is NullPointerException -> liveData.postEmpty()
                        else -> liveData.postThrowable(throwable)
                    }
                }
            }

        }
    }
}