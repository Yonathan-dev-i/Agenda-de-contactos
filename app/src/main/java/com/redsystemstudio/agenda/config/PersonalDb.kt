package com.redsystemstudio.agenda.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.redsystemstudio.agenda.dao.PersonalDao
import com.redsystemstudio.agenda.models.Personal

@Database(
    entities = [Personal::class],
    version = 1
)

abstract class PersonalDb:RoomDatabase() {
    abstract fun personalDao(): PersonalDao
}