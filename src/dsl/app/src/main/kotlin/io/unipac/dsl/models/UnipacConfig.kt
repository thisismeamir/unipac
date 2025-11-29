package io.unipac.dsl.models

import io.unipac.dsl.models.repository.Repository
import io.unipac.dsl.models.repository.RepositoryBuilder
import io.unipac.dsl.models.repository.RepositoryMarker
import io.unipac.dsl.models.universe.Universe
import io.unipac.dsl.models.universe.UniverseBuilder

@RepositoryMarker
class UnipacConfig {
    private val repositories = mutableListOf<Repository>()
    private val universes = mutableListOf<Universe>()

    fun repository(name: String, block: RepositoryBuilder.() -> Unit) {
        repositories.add(RepositoryBuilder(name).apply(block).build())
    }

    fun universe(name: String, block: UniverseBuilder.() -> Unit) {
        universes.add(UniverseBuilder(name).apply(block).build())
    }

    fun getRepositories(): List<Repository> = repositories.toList()
    fun getUniverses(): List<Universe> = universes.toList()
}