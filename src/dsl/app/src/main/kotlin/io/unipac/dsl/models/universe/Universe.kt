package io.unipac.dsl.models.universe

import io.unipac.dsl.models.packages.PackageReference
import io.unipac.dsl.models.repository.Repository

data class Universe(
    val name: String,
    val description: String,
    val packages: List<PackageReference>,
    val repositories: List<Repository>,
    val environmentVariables: Map<String, String>,
    val activationHooks: List<() -> Unit>,
    val deactivationHooks: List<() -> Unit>
)