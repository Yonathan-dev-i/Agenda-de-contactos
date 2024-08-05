package com.redsystemstudio.agenda.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.redsystemstudio.agenda.models.Personal

@Dao
interface PersonalDao {

    @Query("SELECT * FROM personal")
    suspend fun getAll(): List<Personal>

    @Query("SELECT * FROM personal WHERE id = :id")
    suspend fun getById(id: Int): Personal?

    @Insert
    suspend fun insert(personas: ArrayList<Personal>): List<Long>

    @Update
    suspend fun update(personal: Personal): Int

    @Delete
    suspend fun delete(personal: Personal): Int
}
