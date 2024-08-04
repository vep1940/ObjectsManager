package com.vep1940.data.repository

import com.vep1940.data.datasource.ObjectDatasource
import com.vep1940.data.model.toDetailedDomain
import com.vep1940.data.model.toDomain
import com.vep1940.domain.model.DetailedObject
import com.vep1940.domain.model.Object
import com.vep1940.domain.repository.ObjectRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObjectRepositoryImpl(private val objectDatasource: ObjectDatasource) : ObjectRepository {

    override suspend fun getObject(id: Long): Object =
        objectDatasource.getObject(id).toDomain()

    override fun getObjects(): Flow<List<Object>> =
        objectDatasource.getAllObjects().map { it.toDomain() }

    override fun getDetailedObject(id: Long): Flow<DetailedObject> =
        objectDatasource.getObjectByIdWithRelations(id = id).map { it.toDetailedDomain() }

    override suspend fun getPossibleRelations(id: Long): List<Object> =
        objectDatasource.getObjectPossibleRelationsById(id).map { it.toDomain() }

    override suspend fun addObject(name: String, description: String, type: String) {
        objectDatasource.addObject(name = name, description = description, type = type)
    }

    override suspend fun modifyObject(id: Long, name: String, description: String, type: String) {
        objectDatasource.modifyObject(id = id, name = name, description = description, type = type)
    }

    override suspend fun deleteObject(id: Long) {
        objectDatasource.deleteObject(id = id)
    }

    override suspend fun addRelation(objectId1: Long, objectId2: Long) {
        objectDatasource.addRelation(objectId1 = objectId1, objectId2 = objectId2)
    }

    override suspend fun modifyRelation(
        objectId: Long,
        oldObjectId2: Long,
        newObjectId2: Long
    ) {
        objectDatasource.modifyRelation(
            oldObjectId1 = objectId,
            oldObjectId2 = oldObjectId2,
            newObjectId1 = objectId,
            newObjectId2 = newObjectId2,
        )
    }

    override suspend fun removeRelation(objectId1: Long, objectId2: Long) {
        objectDatasource.removeRelation(objectId1 = objectId1, objectId2 = objectId2)
    }
}