package com.nesterov.veld.di.configuration.wrapper

import com.nesterov.veld.common.RequestResult
import com.nesterov.veld.common.ResultHolder
import com.nesterov.veld.network.dnd.config.HttpRequestWrapper
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

class HttpRequestWrapperImpl: HttpRequestWrapper {
    override suspend fun <T> wrapHttpExceptions(block: suspend () -> T): RequestResult<T> {
        return try {
            RequestResult(status = ResultHolder.Success(block()))
        } catch (e: RedirectResponseException) {
            e.printStackTrace()
            RequestResult(status = ResultHolder.Error(e))
        } catch (e: ClientRequestException) {
            e.printStackTrace()
            RequestResult(status = ResultHolder.Error(e))
        } catch (e: ServerResponseException) {
            e.printStackTrace()
            RequestResult(status = ResultHolder.Error(e))
        } catch (e: Exception) {
            e.printStackTrace()
            RequestResult(status = ResultHolder.Error(e))
        }
    }
}