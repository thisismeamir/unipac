package io.unipac.dsl.models.repository

import io.unipac.dsl.models.packages.Package

abstract class Repository {
    abstract val name: String
    abstract val version: String
    abstract val description: String
    abstract val group: RepositoryType
    abstract val mirrors: List<String>
    abstract val credentials: Credentials
    abstract val metadata: Map<String, String>
    abstract val repositoryIdentifier: String

    abstract fun beforeFetch()
    abstract fun afterFetch()
    abstract fun fetchPackage(query: String): List<Package>
    abstract fun search(query: String): List<Package>
    abstract fun getPackage(uniquePackageIdentifier: String): Package?

    abstract fun firstInitialization()
}