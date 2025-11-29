package io.unipac.dsl.models

fun unipac(block: UnipacConfig.() -> Unit): UnipacConfig {
    return UnipacConfig().apply(block)
}