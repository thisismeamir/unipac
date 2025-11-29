package io.unipac.dsl.models.repository

fun repository(name: String, block: RepositoryBuilder.() -> Unit): Repository {
    return RepositoryBuilder(name).apply(block).build()
}