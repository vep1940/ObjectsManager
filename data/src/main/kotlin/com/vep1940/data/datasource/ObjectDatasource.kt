package com.vep1940.data.datasource

import data.ObjectQueries

class ObjectDatasource(private val queries: ObjectQueries) {

    fun getAllObjects() = queries.getAll().executeAsList()

    fun getObjectByIdWithRelations(id: Long) = queries.getByIdWithRelations(id = id).executeAsList()

    fun addObject(name: String, description: String?, type: String) =
        queries.insert(
            objectId = null,
            name = name,
            description = description,
            type = type
        )

    fun modifyObject(id: Long, name: String, description: String?, type: String) =
        queries.modify(name = name, description = description, type = type, objectId = id)

    fun deleteObject(id: Long) = queries.delete(id = id)

    fun addRelation(objectId1: Long, objectId2: Long) =
        queries.insertRelation(
            relationId = null,
            objectId1 = objectId1,
            objectId2 = objectId2
        )

    fun modifyRelation(
        oldObjectId1: Long?,
        oldObjectId2: Long?,
        newObjectId1: Long?,
        newObjectId2: Long?,
    ) = queries.modifyRelation(
        objectId1 = oldObjectId1,
        objectId2 = oldObjectId2,
        objectId1_ = newObjectId1,
        objectId2_ = newObjectId2,
    )

    fun removeRelation(objectId1: Long, objectId2: Long) = queries.deleteRelation(
        objectId1 = objectId1,
        objectId2 = objectId2,
    )
}