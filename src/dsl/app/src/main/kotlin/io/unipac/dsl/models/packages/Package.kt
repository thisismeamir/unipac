package io.unipac.dsl.models.packages

import io.unipac.dsl.models.repository.Repository

data class Package(
    val name: String,
    val version: String,
    val description: String? = null,
    val repository: Repository? = null,
    val uniquePackageIdentifier: String,
    val availableAt: List<Repository> = emptyList(),
    val dependencies: List<Package> = emptyList(),
    val metadata: Map<String, String> = emptyMap(),
    val isDownloaded: Boolean = false,
    val location: String? = null,
    val license: String? = null,
    val packageType: PackageType = PackageType.OTHER
)