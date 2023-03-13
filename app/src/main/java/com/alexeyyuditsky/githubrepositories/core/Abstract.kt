package com.alexeyyuditsky.githubrepositories.core

abstract class Abstract {

    interface Mapper {

        interface DataToDomain<S, R> {
            fun map(data: S): R
            fun map(e: Exception): R
        }

    }

}