package io.unipac.dsl.models.packages

data class PackageReference(
    val name: String,
    val versionConstraint: String = "latest",
    val optional: Boolean = false
)