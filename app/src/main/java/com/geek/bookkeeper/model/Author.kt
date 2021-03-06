package com.geek.bookkeeper.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass
import io.realm.annotations.Required


@RealmClass(embedded=true)
open class Author(

    @Required
    var name: String = "",

    var country: String = "",

    var email: String = ""

): RealmObject() {}