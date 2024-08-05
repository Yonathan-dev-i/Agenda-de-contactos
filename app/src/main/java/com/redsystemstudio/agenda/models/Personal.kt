package com.redsystemstudio.agenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal")
data class Personal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellidos: String,
    val mail: String,
    val telefono: String,
    val edad: Int
)


