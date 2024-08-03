package com.vep1940.domain.repository

import com.vep1940.domain.model.DetailedObject
import com.vep1940.domain.model.Object

interface ObjectRepository {

    fun getObjects(): List<Object>

    fun getDetailedObject(id: Long): DetailedObject?

    fun addObject(name: String, description: String?, type: String)

    fun modifyObject(id: Long, name: String, description: String?, type: String)

    fun deleteObject(id: Long)

    fun addRelation(objectId1: Long, objectId2: Long)

    fun modifyRelation(oldObjectId1: Long, oldObjectId2: Long, newObjectId1: Long, newObjectId2: Long)

    fun removeRelation(objectId1: Long, objectId2: Long)
}