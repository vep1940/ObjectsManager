package com.vep1940.data.datasource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import data.ObjectQueries
import kotlinx.coroutines.CoroutineDispatcher

class ObjectDatasource(
    private val queries: ObjectQueries,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getObject(id: Long) = queries.getById(id = id).executeAsOne()

    fun getAllObjects() = queries.getAll().asFlow().mapToList(dispatcher)

    fun getObjectByIdWithRelations(id: Long) =
        queries.getByIdWithRelations(id).asFlow().mapToList(dispatcher)

    fun getObjectPossibleRelationsById(id: Long) =
        queries.getPossibleRelationsById(id = id).executeAsList()

    fun addObject(name: String, description: String, type: String) =
        queries.insert(
            objectId = null,
            name = name,
            description = description,
            type = type
        )

    fun modifyObject(id: Long, name: String, description: String, type: String) =
        queries.modify(name = name, description = description, type = type, objectId = id)

    fun deleteObject(id: Long) = queries.delete(id = id)

    fun addRelation(objectId1: Long, objectId2: Long) =
        queries.insertRelation(
            relationId = null,
            objectId1 = objectId1,
            objectId2 = objectId2
        )

    fun modifyRelation(
        oldObjectId1: Long,
        oldObjectId2: Long,
        newObjectId1: Long,
        newObjectId2: Long,
    ) {
        removeRelation(oldObjectId1, oldObjectId2)
        addRelation(newObjectId1, newObjectId2)
    }

    fun removeRelation(objectId1: Long, objectId2: Long) {
        queries.deleteRelation(
            objectId1 = objectId1,
            objectId2 = objectId2,
        )
        queries.deleteRelation(
            objectId1 = objectId2,
            objectId2 = objectId1,
        )
    }
}