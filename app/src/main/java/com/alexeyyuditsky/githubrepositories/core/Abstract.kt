package com.alexeyyuditsky.githubrepositories.core

import retrofit2.HttpException
import java.net.UnknownHostException

interface Abstract {

    interface Object<T : Mapper, M> {
        fun map(mapper: T): M
    }

    interface Mapper {

        interface DataToDomain<S, R> {

            fun map(data: S): R
            fun map(e: Exception): R

            fun errorType(e: Exception): ErrorType {
                return when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            }

        }

        interface DomainToUi<S, R> {

            fun map(data: S): R
            fun map(errorType: ErrorType): R

        }

    }

}