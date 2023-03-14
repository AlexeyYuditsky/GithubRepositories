package com.alexeyyuditsky.githubrepositories.core

import retrofit2.HttpException
import java.net.UnknownHostException
import com.alexeyyuditsky.githubrepositories.R

interface Abstract {

    interface Object<T : Mapper, M> {
        fun map(mapper: T): M
    }

    interface Mapper {

        interface DataToDomain<Y, U> : Mapper {

            fun map(data: Y): U
            fun map(e: Exception): U

            fun errorType(e: Exception): ErrorType {
                return when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            }

        }

        abstract class DomainToUi<I, P>(
            private val resourceProvider: ResourceProvider,
        ) : Mapper {

            abstract fun map(data: I): P
            abstract fun map(errorType: ErrorType): P

            fun errorMessage(errorType: ErrorType): String {
                val message = when (errorType) {
                    ErrorType.NO_CONNECTION -> R.string.no_connection_message
                    ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                    else -> R.string.something_went_wrong
                }
                return resourceProvider.getString(message)
            }

        }

    }

}