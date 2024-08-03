package com.vep1940.domain.repository

import com.vep1940.domain.model.DetailedObject
import com.vep1940.domain.model.Object
import kotlinx.coroutines.flow.Flow

interface ObjectRepository {

    fun getObjects(): Flow<List<Object>>

    fun getDetailedObject(id: Long): Flow<DetailedObject>

    suspend fun addObject(name: String, description: String?, type: String)

    suspend fun modifyObject(id: Long, name: String, description: String?, type: String)

    suspend fun deleteObject(id: Long)

    suspend fun addRelation(objectId1: Long, objectId2: Long)

    suspend fun modifyRelation(
        oldObjectId1: Long,
        oldObjectId2: Long,
        newObjectId1: Long,
        newObjectId2: Long
    )

    suspend fun removeRelation(objectId1: Long, objectId2: Long)
}