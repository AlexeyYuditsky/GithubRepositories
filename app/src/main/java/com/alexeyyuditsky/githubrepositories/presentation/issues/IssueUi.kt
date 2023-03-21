package com.alexeyyuditsky.githubrepositories.presentation.issues

sealed class IssueUi {

    open fun map(mapper: ToTextMapper) = Unit
    open fun same(item: IssueUi): Boolean = false
    open fun sameContent(item: IssueUi): Boolean = false

    abstract class Info(
        open val id: Long,
        private val title: String,
    ) : IssueUi() {
        override fun same(item: IssueUi): Boolean {
            return if (item is Base) {
                this.id == item.id
            } else {
                false
            }
        }

        override fun sameContent(item: IssueUi): Boolean {
            return if (item is Base) {
                this.title == item.title
            } else {
                false
            }
        }
    }

    data class Base(
        override val id: Long,
        val title: String,
        val user: String,
        val createdAt: String,
        val updatedAt: String,
    ) : Info(id, title) {
        override fun map(mapper: ToTextMapper) {
            return mapper.map(title, user, createdAt, updatedAt)
        }
    }

    data class Fail(
        val message: String,
    ) : IssueUi() {
        override fun map(mapper: ToTextMapper) {
            mapper.map(message)
        }
    }

    object Progress : IssueUi()

    object Empty : IssueUi()

}