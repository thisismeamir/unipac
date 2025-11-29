package io.unipac.dsl.models.universe

import io.unipac.dsl.models.packages.PackageReference
import io.unipac.dsl.models.repository.Repository
import io.unipac.dsl.models.repository.RepositoryBuilder

@UniverseMarker
class UniverseBuilder(private val name: String) {
    var description: String = ""

    private val packages = mutableListOf<PackageReference>()
    private val repositories = mutableListOf<Repository>()
    private val environmentVariables = mutableMapOf<String, String>()
    private val activationHooks = mutableListOf<() -> Unit>()
    private val deactivationHooks = mutableListOf<() -> Unit>()

    fun pkg(name: String, version: String = "latest", optional: Boolean = false) {
        packages.add(PackageReference(name, version, optional))
    }

    fun packages(vararg pkgs: String) {
        pkgs.forEach { pkg(it) }
    }

    fun repository(repo: Repository) {
        repositories.add(repo)
    }

    fun repository(name: String, block: RepositoryBuilder.() -> Unit) {
        repositories.add(RepositoryBuilder(name).apply(block).build())
    }

    fun env(key: String, value: String) {
        environmentVariables[key] = value
    }

    fun env(pairs: Map<String, String>) {
        environmentVariables.putAll(pairs)
    }

    fun onActivate(block: () -> Unit) {
        activationHooks.add(block)
    }

    fun onDeactivate(block: () -> Unit) {
        deactivationHooks.add(block)
    }

    fun build(): Universe = Universe(
        name = name,
        description = description,
        packages = packages.toList(),
        repositories = repositories.toList(),
        environmentVariables = environmentVariables.toMap(),
        activationHooks = activationHooks.toList(),
        deactivationHooks = deactivationHooks.toList()
    )
}