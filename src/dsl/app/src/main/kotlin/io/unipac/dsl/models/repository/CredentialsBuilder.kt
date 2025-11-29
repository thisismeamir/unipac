package io.unipac.dsl.models.repository

@RepositoryMarker
class CredentialsBuilder {
    var username: String = ""
    var password: String = ""

    fun build(): Credentials = BasicCredentials(username, password)
}