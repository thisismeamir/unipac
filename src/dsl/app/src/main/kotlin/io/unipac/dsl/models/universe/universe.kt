package io.unipac.dsl.models.universe

fun universe(name: String, block: UniverseBuilder.() -> Unit): Universe {
    return UniverseBuilder(name).apply(block).build()
}