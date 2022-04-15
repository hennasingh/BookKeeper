package com.geek.bookkeeper.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId

open class Book(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    @Required
    var name: String = "",

    var isRead: Boolean = false,

    var _partition: String = "",

    var authors: RealmList<Author> = RealmList()
): RealmObject() {}