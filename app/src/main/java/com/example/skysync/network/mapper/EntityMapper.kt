package com.example.skysync.network.mapper


interface EntityMapper<Domain, Entity> {

    fun asEntity(domain: Domain): Entity

}
