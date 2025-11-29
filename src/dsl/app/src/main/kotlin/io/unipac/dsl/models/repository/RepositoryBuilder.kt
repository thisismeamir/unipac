package io.unipac.dsl.models.repository

import io.unipac.dsl.models.packages.Package

@RepositoryMarker
class RepositoryBuilder(private val name: String) {
    var version: String = "1.0.0"
    var description: String = ""
    var group: RepositoryType = RepositoryType.OTHER
    var repositoryIdentifier: String = name

    private val mirrors = mutableListOf<String>()
    private val metadata = mutableMapOf<String, String>()
    private var credentials: Credentials = BasicCredentials("", "")

    private var firstInitialization: () -> Unit = {}
    private var beforeFetchHook: () -> Unit = {}

    private var afterFetchHook: () -> Unit = {}
    private var fetchPackageImpl: (String) -> List<Package> = { emptyList() }
    private var searchImpl: (String) -> List<Package> = { emptyList() }
    private var getPackageImpl: (String) -> Package? = { null }
    fun mirrors(vararg urls: String) {
        mirrors.addAll(urls)
    }

    fun mirror(url: String) {
        mirrors.add(url)
    }

    fun metadata(key: String, value: String) {
        metadata[key] = value
    }

    fun metadata(pairs: Map<String, String>) {
        metadata.putAll(pairs)
    }

    fun credentials(block: CredentialsBuilder.() -> Unit) {
        credentials = CredentialsBuilder().apply(block).build()
    }

    fun firstInitialization(block: () -> Unit) {
        firstInitialization = block
    }
    fun beforeFetch(block: () -> Unit) {
        beforeFetchHook = block
    }

    fun afterFetch(block: () -> Unit) {
        afterFetchHook = block
    }

    fun onFetchPackage(block: (query: String) -> List<Package>) {
        fetchPackageImpl = block
    }

    fun onSearch(block: (query: String) -> List<Package>) {
        searchImpl = block
    }

    fun onGetPackage(block: (identifier: String) -> Package?) {
        getPackageImpl = block
    }

    fun build(): Repository = RepositoryImpl(
        name = name,
        version = version,
        description = description,
        group = group,
        mirrors = mirrors.toList(),
        credentials = credentials,
        metadata = metadata.toMap(),
        repositoryIdentifier = repositoryIdentifier,
        beforeFetchHook = beforeFetchHook,
        afterFetchHook = afterFetchHook,
        fetchPackageImpl = fetchPackageImpl,
        searchImpl = searchImpl,
        getPackageImpl = getPackageImpl
    )
}