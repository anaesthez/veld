package com.nesterov.veld.common.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

open class BaseDatabaseSource {

    /**
//     * Maps database exceptions
//     * @throws Exception if unexpected exception occured
//     */
//    suspend fun <T> wrapDatabaseExceptions(
//        context: CoroutineContext = Dispatchers.Default,
//        block: suspend () -> T,
//    ): Result<T> =
//        withContext(context) {
//            var attempts = 0
//            while (attempts < MAX_TRY_AMOUNT) {
//                try {
//                    return@withContext Result.success(block())
//                } catch (e: Exception) {
//                    attempts++
//                    if (attempts >= MAX_TRY_AMOUNT) {
//                        return@withContext Result.failure(
//                            AppDatabaseException(
//                                "Database operation failed after $attempts " +
//                                        "attempts: ${e::class.simpleName}: ${e.message}"
//                            )
//                        )
//                    }
//                }
//            }
//            return@withContext Result.failure(
//                AppDatabaseException(
//                    "Database operation failed after $attempts attempts: Maximum attempts reached."
//                )
//            )
//        }
//
//    suspend fun <T> wrapDatabaseWithFlow(
//        context: CoroutineContext = Dispatchers.Default,
//        block: suspend () -> T,
//    ): Flow<Result<T>> = flow {
//        var attempts = 0
//        while (attempts < MAX_TRY_AMOUNT) {
//            try {
//                emit(Result.success(block()))
//                break
//            } catch (e: Exception) {
//                attempts++
//                if (attempts >= MAX_TRY_AMOUNT) {
//                    return@flow emit(
//                        Result.failure(
//                            AppDatabaseException(
//                                "Database operation failed after $attempts " +
//                                        "attempts: ${e::class.simpleName}: ${e.message}"
//                            )
//                        )
//                    )
//                }
//            }
//        }
//    }.flowOn(context)

    companion object {
        private const val MAX_TRY_AMOUNT = 3
    }
}