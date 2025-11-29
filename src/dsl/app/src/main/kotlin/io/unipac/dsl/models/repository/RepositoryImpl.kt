package io.unipac.dsl.models.repository

import io.ktor.util.internal.RemoveFirstDesc
import io.unipac.dsl.models.packages.Package

data class RepositoryImpl(
    override val name: String,
    override val version: String,
    override val description: String,
    override val group: RepositoryType,
    override val mirrors: List<String>,
    override val credentials: Credentials,
    override val metadata: Map<String, String>,
    override val repositoryIdentifier: String,
    private val beforeFetchHook: () -> Unit,
    private val afterFetchHook: () -> Unit,
    private val fetchPackageImpl: (String) -> List<Package>,
    private val searchImpl: (String) -> List<Package>,
    private val getPackageImpl: (String) -> Package,
    private val firstInit: () -> Unit
) : Repository() {
    override fun beforeFetch() = beforeFetchHook()
    override fun afterFetch() = afterFetchHook()
    override fun fetchPackage(query: String) = fetchPackageImpl(query)
    override fun search(query: String) = searchImpl(query)
    override fun getPackage(uniquePackageIdentifier: String) = getPackageImpl(uniquePackageIdentifier)
    override fun firstInitialization() =  firstInit()
}