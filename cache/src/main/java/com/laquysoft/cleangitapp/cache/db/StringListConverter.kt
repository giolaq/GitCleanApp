package com.laquysoft.cleangitapp.cache.db

import android.arch.persistence.room.TypeConverter
import com.laquysoft.cleangitapp.cache.model.StringList
import java.util.*

/**
 * Created by joaobiriba on 14/12/2017.
 */
class StringListConverter {
    @TypeConverter
    fun storedStringToStringList(value: String): StringList {
        val langs = value.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }
        return StringList(langs.toList())
    }

    @TypeConverter
    fun stringListToStoredString(cl: StringList): String {
        var value = ""

        for (lang in cl.list)
            value += lang + ","

        return value
    }
}