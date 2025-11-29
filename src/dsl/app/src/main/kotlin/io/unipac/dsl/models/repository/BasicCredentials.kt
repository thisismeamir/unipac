package io.unipac.dsl.models.repository

data class BasicCredentials(
    override val username: String,
    override val password: String
) : Credentials